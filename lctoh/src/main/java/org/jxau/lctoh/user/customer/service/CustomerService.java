package org.jxau.lctoh.user.customer.service;


import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.dao.CustomerDao;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CustomerService")
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 客户登陆
	 * @param user  用户
	 * @return Customer 管理员
	 * @throws UserException
	 * @throws Exception
	 */
	public Customer login (User user) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountError);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Customer customer=customerDao.findCustomerByUserId(_user.getUserId());
		if(customer==null)throw new UserException(ErrorMSG.powerError);
		
		/**后期可能需要修改*/
		if(customer.getCustomerState().getStateId()!=20002)
			throw new UserException(ErrorMSG.loginStateError);
		return customer;
	}
	
	/**
	 * 客户登陆
	 * @param userAccount  用户账号
	 * @param code  验证码
	 * @return Customer 管理员
	 * @throws UserException
	 * @throws Exception
	 */
	public Customer loginByCode(String userAccount,String code) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(userAccount);
		if(_user==null) throw new UserException(ErrorMSG.accountError);
		
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||verificationCode.getVerificationCode().equals(code))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>ErrorMSG.timeExpire)
			throw new UserException(ErrorMSG.timeExpireError);
		Customer customer=customerDao.findCustomerByUserId(_user.getUserId());
		if(customer==null)throw new UserException(ErrorMSG.powerError);
		
		/**后期可能需要修改*/
		if(customer.getCustomerState().getStateId()!=20002)
			throw new UserException(ErrorMSG.loginStateError);
		return customer;
	}
	
	
	
	
	
	
	
	public Customer findCustomerByUserId(String customerUserId){
		return customerDao.findCustomerByUserId(customerUserId);
	}
	
	

}
