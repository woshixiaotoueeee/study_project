package org.jxau.lctoh.user.basis.service;




import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	
	
	/**
	 * 根据邮箱和验证码查询用户信息
	 * @param userEmail
	 * @param code
	 * @return
	 * @throws UserException 
	 */
	public User findByEmailAndCode(String userEmail, String code) throws UserException {
		User _user=userDao.findUserByUserEmail(userEmail);
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||!(verificationCode.getVerificationCode().equals(code)))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>SuccessMSG.timeExpire)
			throw new UserException(ErrorMSG.codeTimeExpire);
		return _user;
	}


	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws UserException 
	 */
	public Integer updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	
}
