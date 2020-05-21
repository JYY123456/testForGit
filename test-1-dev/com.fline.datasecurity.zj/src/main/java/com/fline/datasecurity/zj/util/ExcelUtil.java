package com.fline.datasecurity.zj.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fline.datasecurity.zj.exception.ExcelException;
import com.fline.datasecurity.zj.exception.FlineException;

import lombok.extern.slf4j.Slf4j;

/**
 * 导出成为excel表格
 * 
 * @author zhuzl
 *
 */
@Slf4j
public class ExcelUtil {

	/**
	 * 表单的描述信息
	 * 
	 * @author zhuzl
	 * @date 2020年3月3日
	 *
	 */
	private static class SheetSchema {
		private Row headRow;
		private Map<String, Integer> colIndex;

		public Row getHeadRow() {
			return headRow;
		}

		public void setHeadRow(Row headRow) {
			this.headRow = headRow;
		}

		public Map<String, Integer> getColIndex() {
			return colIndex;
		}

		public void setColIndex(Map<String, Integer> colIndex) {
			this.colIndex = colIndex;
		}

		public int getAutoIncreColIndex() {
			if (colIndex != null && colIndex.containsKey("序号")) {
				return colIndex.get("序号");
			} else {
				return -1;
			}
		}
	}

	/**
	 * 将对象列表导出成Excel文档
	 * 
	 * @param templatePath 模板文件路径
	 * @param fieldsMap    对象字段与excel中字段的关系
	 * @param objs         对象列表
	 * @return
	 */
	public static <T> ByteArrayOutputStream createExcel(String templatePath, Map<String, String> fieldsMap,
			List<T> objs) {
		if (fieldsMap == null || fieldsMap.isEmpty()) {
			log.error("未指定导出的字段映射关系，导出数据将为空");
		}
		// 创建Excel文件
		try (Workbook workBook = new XSSFWorkbook();) {

			CellStyle cellStyle = workBook.createCellStyle();
			cellStyle.setWrapText(true);
			// 加载模板
			List<SheetSchema> schemas = loadExcelTemplate(templatePath);
			if (!schemas.isEmpty()) {
				SheetSchema schema = schemas.get(0);
				try {
					writeSheetContent(workBook, schema, fieldsMap, objs);
				} catch (Exception e) {
					log.error("write sheet content failed.", e);
					throw new ExcelException("-1", e);
				}
			}
			// 文件内容导出
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				workBook.write(outputStream);
			} catch (Exception e) {
				log.error("Excel文件内容写入失败, templatePath = {}", templatePath);
				throw e;
			}

			return outputStream;
		} catch (Exception e1) {
			throw new ExcelException("11000", e1);
		}

	}

	/**
	 * 复制一个单元格样式到目的单元格样式
	 * 
	 * @param fromStyle
	 * @param toStyle
	 */
	public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
		toStyle.setAlignment(fromStyle.getAlignment());
		// 边框和边框颜色
		toStyle.setBorderBottom(fromStyle.getBorderBottom());
		toStyle.setBorderLeft(fromStyle.getBorderLeft());
		toStyle.setBorderRight(fromStyle.getBorderRight());
		toStyle.setBorderTop(fromStyle.getBorderTop());
		toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
		toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
		toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
		toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

		// 背景和前景
		toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
		toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

		toStyle.setDataFormat(fromStyle.getDataFormat());
		toStyle.setFillPattern(fromStyle.getFillPattern());
		// toStyle.setFont(fromStyle.getFont(null));
		toStyle.setHidden(fromStyle.getHidden());
		toStyle.setIndention(fromStyle.getIndention());// 首行缩进
		toStyle.setLocked(fromStyle.getLocked());
		toStyle.setRotation(fromStyle.getRotation());// 旋转
		toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
		toStyle.setWrapText(fromStyle.getWrapText());

	}

	/**
	 * 载入excel的内容，以对象数组的方式返回。注：只加载sheet1
	 * 
	 * @param contentIs
	 * @param fieldsMap
	 * @param cls
	 * @return
	 * @throws ExcelException
	 */
	public static <T> List<T> readExcelContent(InputStream contentIs, Map<String, String> fieldsMap, Class<T> cls) {
		try (Workbook workbook = new XSSFWorkbook(contentIs)) {
			int sheetNum = workbook.getNumberOfSheets();
			if (sheetNum <= 0) {
				throw new ExcelException("11004");
			}
			Sheet sheet1 = workbook.getSheetAt(0);
			return readSheetContent(sheet1, fieldsMap, cls);
		} catch (Exception e) {
			throw new ExcelException("11000", e);
		}
	}

	/**
	 * 从excel表中加载sheet 对应的业务数据
	 * 
	 * @param sheet     excel sheet对象
	 * @param fieldsMap 字段映射关系
	 * @param cls       返回对象的类
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readSheetContent(Sheet sheet, Map<String, String> fieldsMap, Class<T> cls) {
		if (sheet == null) {
			throw ExcelException.EMPTY_CONTENT;
		}
		if (fieldsMap == null || fieldsMap.isEmpty()) {
			throw ExcelException.EMPTY_MAPPING;
		}
		try {
			// 解析表头
			Row row = sheet.getRow(0);
			Map<String, Integer> colIndexMap = new HashMap<>();
			int startColIndex = row.getFirstCellNum();
			int endColIndex = row.getLastCellNum();
			for (int j = startColIndex; j < endColIndex; j++) {
				String item = row.getCell(j).getStringCellValue();
				if (StringUtils.isEmpty(item)) {
					continue;
				} else {
					colIndexMap.put(item, j);
				}
			}
			// 转成index - feld
			Map<Integer, Field> fieldMap = loadFieldMap(colIndexMap, fieldsMap, cls);
			// 载入业务数据
			int endLineNum = sheet.getLastRowNum();
			List<T> objs = new ArrayList<>();
			for (int i = 1; i <= endLineNum; i++) {
				T obj = cls.newInstance();
				fillObj(sheet.getRow(i), startColIndex, endColIndex, obj, fieldMap);
				objs.add(obj);
			}
			return objs;
		} catch (Exception e) {
			throw new ExcelException("11000", e);
		}
	}

	/**
	 * 将excel中的字段映射关系 转化成 java中的field
	 * 
	 * @param colIndexMap key = 中文名， value = index
	 * @param fieldsMap   key = 字段名称， value = excel中的中文名
	 * @param cls         返回的对象类
	 * @return
	 * @throws Exception
	 */
	private static <T> Map<Integer, Field> loadFieldMap(Map<String, Integer> colIndexMap, Map<String, String> fieldsMap,
			Class<T> cls) throws Exception {
		Map<Integer, Field> index2FieldMap = new HashMap<>();
		// 缺少字段映射，则无方法映射
		if (colIndexMap == null || fieldsMap == null || fieldsMap.isEmpty() || colIndexMap.isEmpty()) {
			return index2FieldMap;
		}
		if (cls == null) {
			throw new Exception("错误的实体类");
		}
		Field[] fields = cls.getDeclaredFields();
		// 实体对象未定义字段， 则无方法映射
		if (fields == null || fields.length <= 0) {
			return index2FieldMap;
		}
		for (Field f : fields) {
			String fieldName = f.getName();
			if (fieldsMap.containsKey(fieldName)) {
				Integer index = colIndexMap.get(fieldsMap.get(fieldName));
				if (index == null) {
					// 么有出现在表格中
					continue;
				} else {
					// 支持直连
					f.setAccessible(true);
					index2FieldMap.put(index, f);
				}
			}
		}
		return index2FieldMap;

	}

	/**
	 * 填充对象
	 * 
	 * @param r
	 * @param obj
	 * @param colIndexMap
	 * @param fieldsMap
	 * @throws ExcelException
	 */
	private static <T> void fillObj(Row r, int startColIndex, int endColIndex, T obj, Map<Integer, Field> fieldMap) {
		if (r == null) {
			return;
		}
		for (int i = startColIndex; i < endColIndex; i++) {
			Field f = fieldMap.get(i);
			if (f == null) {
				continue;
			}
			Cell cell = r.getCell(i);
			if (cell == null) {
				continue;
			}
			String cellValue = cell.getStringCellValue();
			String cellStrValue = cellValue == null ? "" : cellValue.trim();
			try {
				Object targetObject = toTargetType(f.getType(), cellStrValue);
				f.set(obj, targetObject);
			} catch (Exception e) {
				throw new ExcelException("11000", e);
			}
		}
	}

	/**
	 * 将excel中的字符串形式的内容转成目标形式的类型
	 * 
	 * @param targetType
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static Object toTargetType(Class<?> targetType, String value) throws Exception {
		if (targetType == String.class) {
			return value;
		} else if (targetType == int.class || targetType == Integer.class) {
			if (StringUtils.isEmpty(value)) {
				return 0;
			} else {
				return Integer.valueOf(value);
			}
		} else if (targetType == long.class || targetType == Long.class) {
			if (StringUtils.isEmpty(value)) {
				return 0l;
			} else {
				return Long.valueOf(value);
			}
		} else if (targetType == float.class || targetType == Float.class) {
			if (StringUtils.isEmpty(value)) {
				return 0f;
			} else {
				return Float.valueOf(value);
			}
		} else if (targetType == double.class || targetType == Double.class) {
			if (StringUtils.isEmpty(value)) {
				return 0d;
			} else {
				return Double.valueOf(value);
			}
		} else if (targetType == boolean.class || targetType == Boolean.class) {
			return !(value.equalsIgnoreCase("false") || value.equals("0"));
		} else if (targetType == Date.class) {
			return DateUtils.parseDate(value, "yyyy-MM-dd");
		} else {
			FlineException flineException = new FlineException("unsupport targettype = ");
			flineException.setObjects(targetType.getName());
			throw flineException;
		}
	}

	private static void writeSheetContent(Workbook workbook, SheetSchema schema, Map<String, String> fieldsMap,
			List<?> objs) throws ExcelException {
		// 创建Sheet
		Sheet sheet = workbook.createSheet();
		if (schema == null || schema.getColIndex().isEmpty() || schema.getHeadRow() == null) {
			// 未加载到字段
			return;
		}
		if (fieldsMap == null || fieldsMap.isEmpty() || objs == null || objs.isEmpty()) {
			// 未加载到内容
			return;
		}
		// 填写首行的字段
		Row headRow = schema.getHeadRow();
		Row row = sheet.createRow(0);
		for (int j = headRow.getFirstCellNum(); j < headRow.getLastCellNum(); j++) {
			Cell cell = row.createCell(j);
			cell.setCellValue(headRow.getCell(j).getStringCellValue());
			copyCellStyle(headRow.getCell(j).getCellStyle(), cell.getCellStyle());
		}
		// 填写内容
		int rowIndex = 1;
		int autoIncreColIndex = schema.getAutoIncreColIndex();
		Map<String, Integer> colIndex = schema.getColIndex();
		for (Object obj : objs) {
			Row contentRow = sheet.createRow(rowIndex++);
			try {
				JSONObject jsonObj = (JSONObject) JSON.toJSON(obj);
				for (Entry<String, String> kv : fieldsMap.entrySet()) {
					Integer cellIndex = colIndex.get(kv.getValue());
					if (cellIndex == null) {
						continue;
					} else {
						String value = jsonObj.getString(kv.getKey());
						Cell cell = contentRow.createCell(cellIndex);
						cell.setCellValue(value);
					}
				}
				if (autoIncreColIndex >= 0) {
					Cell cell = contentRow.createCell(autoIncreColIndex);
					cell.setCellValue(String.valueOf(rowIndex - 1));
				}
			} catch (Exception e) {
				log.error("write row content failed. " + e.getMessage() + ", rowIndex = " + rowIndex);
			}
		}
	}

	/**
	 * 加载模板的元数据信息
	 * 
	 * @param templatePath
	 * @return
	 */
	private static List<SheetSchema> loadExcelTemplate(String templatePath) {
		List<SheetSchema> sheetSchemas = new ArrayList<>();
		// 入参校验
		File f = new File(templatePath);
		if (!f.exists()) {
			log.error("模板文件不存在. 文件路径 = {}", templatePath);
		} else {
			// 加载模板文件
			try (Workbook workbook = new XSSFWorkbook(new FileInputStream(f))) {
				// 加载所有的sheet
				int sheetNum = workbook.getNumberOfSheets();
				for (int i = 0; i < sheetNum; i++) {
					SheetSchema schema = new SheetSchema();
					Sheet sheet = workbook.getSheetAt(i);
					Row row = sheet.getRow(0);
					if (row == null) {
						continue;
					}
					Map<String, Integer> colIndex = new HashMap<>();
					for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
						String item = row.getCell(j).getStringCellValue();
						if (StringUtils.isEmpty(item)) {
							continue;
						} else {
							colIndex.put(item, j);
						}
					}
					schema.setColIndex(colIndex);
					schema.setHeadRow(row);
					sheetSchemas.add(schema);
				}
			} catch (Exception e) {
				log.error("parse template failed.", e);
			}
		}
		return sheetSchemas;
	}

	public static void main(String[] args) throws IOException {
		String templatePath = "E:\\workspace\\work\\com.fline.resumption.runtime\\web\\eventManagement\\模板.xlsx";
		List<JSONObject> datas = new ArrayList<>();
		JSONObject row1 = new JSONObject();
		row1.put("sfzh", "33048319904");
		datas.add(row1);

		JSONObject row2 = new JSONObject();
		row2.put("sfzh", "12XXFWER232");
		row2.put("hb", "someH");
		datas.add(row2);

		Map<String, String> fieldsMap = new HashMap<>();
		fieldsMap.put("sfzh", "身份证号（必填：18位）");
		fieldsMap.put("hb", "返回火车车次/航班号/车牌号");
		ByteArrayOutputStream out = createExcel(templatePath, fieldsMap, datas);
		try (FileOutputStream fos = new FileOutputStream(new File("E:/test.xlsx"));) {
			fos.write(out.toByteArray());
		}
	}

}
