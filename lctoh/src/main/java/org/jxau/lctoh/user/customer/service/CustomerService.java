package org.jxau.lctoh.user.customer.service;


import org.jxau.lctoh.state.dao.StateDao;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.ClassInfoMSG;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qdt_PC
 *
 */
/**
 * @author qdt_PC
 *
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

	
	/**
	 * 注册
	 * @param user
	 * @return
	 * @throws UserException
	 */
	@Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
	public User register(User user) throws UserException {
		if(userDao.findUserByUserEmail(user.getUserEmail())!=null){
			throw new UserException(ErrorMSG.emailExistenceError);
		}
		/**
		 * 省略电话号码验证
		 * */
		user.setUserId(Tools.getRandomString(32));
		user.setUserCode(Tools.getRandomString(32));
		user.setUserAccount(Tools.getRandomNumberString(10));
		
		userDao.addUser(user);
		
		Customer customer=new Customer();
		customer.setCustomerUser(user);
		customer.setCustomerId(Tools.getRandomString(32));
		customer.setCustomerBalance(ClassInfoMSG.customerBalance);
		customer.setCustomerNickname(ClassInfoMSG.customerNickname);
		customer.setCustomerPortrait(ClassInfoMSG.customerPortrait);
		customer.setCustomerState(stateDao.findStateByStateId(ClassInfoMSG.customerStateId));
		
		customerDao.addCustomer(customer);
		
		return user;
	}
	
	

}
