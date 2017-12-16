package org.jxau.lctoh.user.customer.service;

import org.jxau.lctoh.user.customer.dao.CustomerDao;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CustomerService")
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	
	public Customer findCustomerByUserId(String customerUserId){
		return customerDao.findCustomerByUserId(customerUserId);
	}
	

}
