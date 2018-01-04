package org.jxau.lctoh.user.basis.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 验证码基础类
 * @author qdt_PC
 */
@Component
@Alias("VerificationCode")
@Scope("prototype")
public class VerificationCode {
	private String verificationCodeId;
	private String verificationCode;
	private Date verificationCodeUpdateTime;
	public String getVerificationCodeId() {
		return verificationCodeId;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public Date getVerificationCodeUpdateTime() {
		return verificationCodeUpdateTime;
	}
	public void setVerificationCodeId(String verificationCodeId) {
		this.verificationCodeId = verificationCodeId;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public void setVerificationCodeUpdateTime(Date verificationCodeUpdateTime) {
		this.verificationCodeUpdateTime = verificationCodeUpdateTime;
	}
	@Override
	public String toString() {
		return "VerificationCode [verificationCodeId=" + verificationCodeId
				+ ", verificationCode=" + verificationCode
				+ ", verificationCodeUpdateTime=" + verificationCodeUpdateTime
				+ "]";
	}
	
}
