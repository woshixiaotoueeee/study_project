package org.jxau.lctoh.user.admin.service;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.admin.dao.AdminDao;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author qdt_PC
 */
@Service("AdminService")
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	
	/**
	 * 管理员登陆
	 * @param user  用户
	 * @return Admin 管理员
	 * @throws UserException
	 */
	public Admin login (User user) throws UserException{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Admin admin=adminDao.findAdminByUserId(_user.getUserId());
		if(admin==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(admin.getAdminState().getStateId()!=10002)
			throw new UserException(ErrorMSG.loginStateError);
		return admin;
	}
	/**
	 * 管理员登陆
	 * @param userAccount  用户账号
	 * @param code  验证码
	 * @return Admin 管理员
	 * @throws UserException
	 */
	public Admin loginByCode (String userAccount,String code) throws UserException{
		
		User _user=userDao.findUserByUserAccount(userAccount);
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||!(verificationCode.getVerificationCode().equals(code)))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>SuccessMSG.timeExpire)
			throw new UserException(ErrorMSG.codeTimeExpire);
		Admin admin=adminDao.findAdminByUserId(_user.getUserId());
		if(admin==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(admin.getAdminState().getStateId()!=10002)
			throw new UserException(ErrorMSG.loginStateError);
		return admin;
	}
	
}
