package org.jxau.lctoh.user.customer.service;


import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.domain.User;
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
	private UserDao userDao;
	/**
	 * 客户登陆
	 * @param userId  用户识别码
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
	
	
	
	
	
	
	
	
	
	public Customer findCustomerByUserId(String customerUserId){
		return customerDao.findCustomerByUserId(customerUserId);
	}
	
	

}
