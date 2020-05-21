package com.fline.datasecurity.zj.vo;

import java.io.Serializable;
import java.util.Date;

//import com.fline.framework.webdev.dao.entity.Department;
//import com.fline.framework.webdev.dao.entity.Organization;

import lombok.Data;

/**
 * 系统用户
 * 
 * @author zhuzl
 * @date 2020年2月11日
 *
 */
@Data
public class UserVo implements Serializable {

	/** 用户ID */
	private long id;
	/** 登陆名 */
	private String username;
	/** 密码 */
	private String password;
	/** 显示账号 */
	private String accountName;
	/** 账号状态 */
	private boolean active = false;
	/** 有效期限 */
	private Date expiredDate;
	/** 密码有效期限 */
	private Date passwordExpiredDate;

	// 以下为用户基础信息
	/** 移动电话 */
	private String mobilePhone;
	/** 办公电话 */
	private String officePhone;
	/** Email */
	private String email;
	/** 工号 */
	private String workNo;
	/** 职位 */
	private String position;
	/** 手机虚拟号 */
	private String virtualMobilePhone;
	/** 电话(分机) */
	private String extension;
	/** 传真 */
	private String fax;
	/** 是否证书用户 */
	private boolean isCertificateUser;
	/** 排序码 */
	private String ordinal;

	// 生命周期
	/** 创建时间 */
	private Date created;
	/** 创建人 */
	private long creator;
	/** 更新时间 */
	private Date updated;
	/** 更新人 */
	private long updater;

//	/** 关联部门 */
//	private Department department;
//	/** 关联的组织 */
//	private Organization organization;

}
