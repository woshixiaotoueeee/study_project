package org.jxau.lctoh.user.basis.mapper;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.basis.domain.VerificationCode;

/**
 * 用户类代理接口
 * @author qdt_PC
 */
public interface VerificationCodeMapper {

	/**
	 * 根据验证码识别码获得验证码信息
	 * @param id  验证码识别码
	 * @return VerificationCode 
	 */
	public VerificationCode findVerificationCodeById(@Param("id")String id);
	
	/**
	 * 
	 * @param verificationCode
	 * @return Integer 
	 */
	public Integer replaceVerificationCode(VerificationCode verificationCode);
	
	
}
