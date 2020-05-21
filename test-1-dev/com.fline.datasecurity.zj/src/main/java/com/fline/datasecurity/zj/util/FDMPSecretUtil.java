package com.fline.datasecurity.zj.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 大数据管理平台的接口调用所需的密钥管理工具
 * 
 * @author zhuzl
 *
 */
public class FDMPSecretUtil {
	private static Logger LOG = LogManager.getLogger(FDMPSecretUtil.class);

	public static String refreshSecretKey(String secretUrl, String appKey, String tenantCode, String password) {
		String sign = new SM3Util().sm3(tenantCode + "#" + appKey + "#" + password);
		Map<String, String> param = new HashMap<String, String>();
		param.put("tenantCode", tenantCode);
		param.put("appKey", appKey);
		param.put("sign", sign);
		String secretKeyInfo = HttpClientUtil.doPost(secretUrl, param);
		try {
			LOG.debug("secretKeyInfo：" + secretKeyInfo);
			String secretKey = SymmetricEncoder.AESDncode(appKey,
					JSONObject.parseObject(secretKeyInfo).getJSONObject("data").getString("secretKey"));
			LOG.debug("secretKey：" + secretKey);
			return secretKey;
		} catch (Exception e) {
			LOG.warn("refresh secret key failed." + e.getMessage());
			return null;
		}

	}

	public static void main(String[] args) {
		String secretUrl = "http://172.16.23.200:8002/aic/rest/service/safety/refreshsecret";
		// appKey样例（供测试）
		String appKey = "ffad72ed-ee87-46eb-814d-17e3111a7505";
		// 租户编码样例（供测试）
		String tenantCode = "U0022Z0001";
		// 密码样例（供测试）
		String password = "123456";
		refreshSecretKey(secretUrl, appKey, tenantCode, password);
	}
}
