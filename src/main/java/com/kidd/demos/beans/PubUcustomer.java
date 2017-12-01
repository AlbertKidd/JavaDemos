package com.kidd.demos.beans;

import lombok.Data;

/**
 * 
 * @author generator
 * 
 */
@Data
public class PubUcustomer {
	/**
	* 统一客户编号
	*/
	private String ucustomerid;

	/**
	* 客户名称
	*/
	private String customername;

	/**
	* 机器码
	*/
	private String machinecode;

	/**
	* 机器名
	*/
	private String reg_machinename;

	/**
	* 机器码验证码
	*/
	private String midcode;

	/**
	* 密码验证码
	*/
	private String pidcode;

	/**
	* 电子邮件
	*/
	private String email;

	/**
	* 微信号码
	*/
	private String wechatno;

	/**
	* QQ号码
	*/
	private String qqno;

	/**
	* 联系人
	*/
	private String linkman;

	/**
	* 手机
	*/
	private String mobile;

	/**
	* 注册地址
	*/
	private String linkaddress;

	/**
	* 备注
	*/
	private String memo;

	/**
	* 创建日期
	*/
	private java.sql.Timestamp credate;

	/**
	* 修改日期
	*/
	private java.sql.Timestamp editdate;

}


