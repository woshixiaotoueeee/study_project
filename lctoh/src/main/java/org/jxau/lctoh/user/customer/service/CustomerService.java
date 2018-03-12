package org.jxau.lctoh.user.customer.service;


import java.util.List;

import org.jxau.lctoh.state.dao.StateDao;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.defaultInformation.DefaultInformation;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.admin.domain.QueryWebModel;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.dao.CustomerDao;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qdt_PC
 */
@Service("CustomerService")
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private StateDao stateDao;
	/**
	 * 客户登陆
	 * @param user  用户
	 * @return Customer 管理员
	 * @throws UserException
	 * @throws Exception
	 */
	public Customer login (User user) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Customer customer=customerDao.findCustomerByUserId(_user.getUserId());
		if(customer==null)throw new UserException(ErrorMSG.noPower);
		
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
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||verificationCode.getVerificationCode().equals(code))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>SuccessMSG.timeExpire)
			throw new UserException(ErrorMSG.codeTimeExpire);
		Customer customer=customerDao.findCustomerByUserId(_user.getUserId());
		if(customer==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(customer.getCustomerState().getStateId()!=20002)
			throw new UserException(ErrorMSG.loginStateError);
		return customer;
	}
	
	
	
	/**
	 * 根据客户ID查询客户信息
	 * @param customerUserId
	 * @return Customer
	 */
	public Customer findCustomerByUserId(String customerUserId){
		return customerDao.findCustomerByUserId(customerUserId);
	}

	
	/**
	 * 注册
	 * @param user
	 * @return User
	 * @throws UserException
	 */
	@Transactional(rollbackFor = Exception.class)    
	public User addCustomerRegister(User user) throws UserException {
		if(userDao.findUserByUserEmail(user.getUserEmail())!=null){
			throw new UserException(ErrorMSG.emailExistence);
		}
		/**
		 * 省略电话号码验证
		 * */
		//补全信息
		user.setUserId(Tools.getRandomString(32));
		user.setUserCode(Tools.getRandomString(32));
		user.setUserAccount(Tools.getRandomNumberString(10));
		userDao.addUser(user);
		
		//补全基本信息
		Customer customer=new Customer();
		customer.setCustomerUser(user);
		customer.setCustomerId(Tools.getRandomString(32));
		customer.setCustomerBalance(DefaultInformation.customerBalance);
		customer.setCustomerNickname(DefaultInformation.customerNickname);
		customer.setCustomerPortrait(DefaultInformation.customerPortrait);
		customer.setCustomerState(stateDao.findStateByStateId(DefaultInformation.customerStateId));
		customerDao.addCustomer(customer);
		
		return user;
	}

	/**
	 * 更新用户信息
	 * @param customer
	 * @throws UserException 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCustomer(Customer customer) throws UserException {
		User user=customer.getCustomerUser();
		if(userDao.findUserByUserEmail(user.getUserEmail())!=null){
			throw new UserException(ErrorMSG.emailExistence);
		}
		/**
		 * 省略电话号码验证
		 * */
		userDao.updateUser(user);
		customerDao.updateCustomer(customer);
	}
	
	
	public List<Customer> getCustomer(QueryWebModel qwm){
		return customerDao.getCustomer(qwm);
	}

	public void lockAccount(String customerid, int i) {
		
		
	}
	
}
