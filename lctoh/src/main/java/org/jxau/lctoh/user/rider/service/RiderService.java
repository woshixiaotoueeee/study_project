package org.jxau.lctoh.user.rider.service;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.rider.dao.RiderDao;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	 * @throws Exception
	 */
	public Rider login (User user) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountError);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Rider rider=riderDao.findRiderByUserId(_user.getUserId());
		if(rider==null)throw new UserException(ErrorMSG.powerError);
		
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
	 * @throws Exception
	 */
	public Rider loginByCode (String userAccount,String code) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(userAccount);
		if(_user==null) throw new UserException(ErrorMSG.accountError);
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||verificationCode.getVerificationCode().equals(code))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>ErrorMSG.timeExpire)
			throw new UserException(ErrorMSG.timeExpireError);
		Rider rider=riderDao.findRiderByUserId(_user.getUserId());
		if(rider==null)throw new UserException(ErrorMSG.powerError);
		
		/**后期可能需要修改*/
		if(rider.getRiderState().getStateId()!=10002)
			throw new UserException(ErrorMSG.loginStateError);
		return rider;
	}
}
