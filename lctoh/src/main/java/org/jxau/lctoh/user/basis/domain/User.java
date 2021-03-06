package org.jxau.lctoh.user.basis.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 基础用户类
 * @author qdt_PC
 */
@Component
@Alias("User")
@Scope("prototype")
public class User {
	/**用户识别码*/
	private String userId;
	/**账号*/
	private String userAccount;
	/**密码*/
	private String userPassword;
	/**性别*/
	private String userSex;
	/**邮箱*/
	private String userEmail;
	/**电话*/
	private String userPhone;
	/**最近更新时间*/
	private Date userUpdateTime;
	/**激活码*/
	private String userCode;
	
	public String getUserId() {
		return userId;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Date getUserUpdateTime() {
		return userUpdateTime;
	}
	public void setUserUpdateTime(Date userUpdateTime) {
		this.userUpdateTime = userUpdateTime;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userSex=" + userSex
				+ ", userEmail=" + userEmail + ", userPhone=" + userPhone
				+ ", userUpdateTime=" + userUpdateTime + ", userCode="
				+ userCode + "]";
	}
	
	
}
