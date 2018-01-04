package org.jxau.lctoh.user.basis.dao;


import java.util.Date;



import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.mapper.VerificationCodeMapper;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("VerificationCodeDao")
public class VerificationCodeDao extends BaseDao{
	private VerificationCodeMapper verificationCodeMapper;
	
	public VerificationCodeMapper getVerificationCodeMapper() {
		return verificationCodeMapper;
	}
	public void setVerificationCodeMapper(
			VerificationCodeMapper verificationCodeMapper) {
		this.verificationCodeMapper = verificationCodeMapper;
	}
	@Override
	public void puttMapper() {
		verificationCodeMapper=this.getMapper(VerificationCodeMapper.class);
	}
	/**
	 * 根据验证码识别码获得验证码信息
	 * @param id  验证码识别码
	 * @return VerificationCode 
	 */
	public VerificationCode findVerificationCodeById(String id){
		return verificationCodeMapper.findVerificationCodeById(id);
	}
	
	/**
	 * 更新验证码信息
	 * @param verificationCode
	 * @return Integer 
	 */
	public Integer replaceVerificationCode(VerificationCode verificationCode){
		verificationCode.setVerificationCodeUpdateTime(new Date());
		return verificationCodeMapper.replaceVerificationCode(verificationCode);
	}
	
}
