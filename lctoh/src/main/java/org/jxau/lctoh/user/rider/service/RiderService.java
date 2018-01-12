package org.jxau.lctoh.user.rider.service;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.rider.dao.RiderDao;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author qdt_PC
 */
@Service("RiderService")
public class RiderService {
	@Autowired
	private RiderDao riderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	
	
	/**
	 * 管理员登陆
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 * @throws UserException
	 */
	public Rider login (User user) throws UserException{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Rider rider=riderDao.findRiderByUserId(_user.getUserId());
		if(rider==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(rider.getRiderState().getStateId()!=10002)
			throw new UserException(ErrorMSG.loginStateError);
		return rider;
	}
	/**
	 * 管理员登陆
	 * @param userAccount  用户账号
	 * @param code  验证码
	 * @return Admin 管理员
	 * @throws UserException
	 */
	public Rider loginByCode (String userAccount,String code) throws UserException{
		
		User _user=userDao.findUserByUserAccount(userAccount);
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||verificationCode.getVerificationCode().equals(code))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>SuccessMSG.timeExpire)
			throw new UserException(ErrorMSG.codeTimeExpire);
		Rider rider=riderDao.findRiderByUserId(_user.getUserId());
		if(rider==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(rider.getRiderState().getStateId()!=10002)
			throw new UserException(ErrorMSG.loginStateError);
		return rider;
	}
}
