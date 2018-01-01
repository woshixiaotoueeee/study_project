package org.jxau.lctoh.user.basis.service;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.EmailConfing;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.service.EmailService;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.VerificationCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VerificationCodeService")
public class VerificationCodeService {
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VerificationCode verificationCode;
	@Autowired
	private EmailService emailService;
	/**设置验证码*/
	public VerificationCode setCode(String userAccount) throws VerificationCodeException{
		User _user=userDao.findUserByUserAccount(userAccount);
		if(_user==null)throw new VerificationCodeException(ErrorMSG.accountError);
		
		verificationCode.setVerificationCode(Tools.getRandomString(6));
		verificationCode.setVerificationCodeId(_user.getUserId());
		try {
			verificationCodeDao.replaceVerificationCode(verificationCode);
		} catch (Exception e) {
			throw new VerificationCodeException(ErrorMSG.notKnowError);
		}
		
		try {
			emailService.sendEmail(_user.getUserEmail(), EmailConfing.popTile, EmailConfing.sendMSGCodeTile, EmailConfing.sendMSGCodeInfo.concat(verificationCode.getVerificationCode()));
		} catch (Exception e) {
			throw new VerificationCodeException(ErrorMSG.emailSendError);
		}
		return verificationCode;
	}
	
	/**
	 * 根据邮箱设置验证码
	 * @param userEmail
	 * @return
	 * @throws VerificationCodeException
	 */
	public VerificationCode setCodeByUserEmail(String userEmail) throws VerificationCodeException {
		User _user=userDao.findUserByUserEmail(userEmail);
		if(_user==null)throw new VerificationCodeException(ErrorMSG.accountError);
		
		verificationCode.setVerificationCode(Tools.getRandomString(6));
		verificationCode.setVerificationCodeId(_user.getUserId());
		try {
			verificationCodeDao.replaceVerificationCode(verificationCode);
		} catch (Exception e) {
			throw new VerificationCodeException(ErrorMSG.notKnowError);
		}
		
		try {
			emailService.sendEmail(_user.getUserEmail(), EmailConfing.popTile, EmailConfing.sendMSGCodeTile, EmailConfing.sendMSGCodeInfo.concat(verificationCode.getVerificationCode()));
		} catch (Exception e) {
			throw new VerificationCodeException(ErrorMSG.emailSendError);
		}
		return verificationCode;
		
	}
}
