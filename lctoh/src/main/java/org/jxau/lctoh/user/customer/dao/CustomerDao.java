package org.jxau.lctoh.user.customer.dao;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.user.admin.domain.QueryWebModel;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("CustomerDao")
public class CustomerDao extends BaseDao {
	@Autowired
	private CustomerMapper customerMapper;
	
	public CustomerMapper getCustomerMapper() {
		return customerMapper;
	}
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	@Override
	public void puttMapper() {
		customerMapper=getMapper(CustomerMapper.class);
	}
	
	/**根据用户识别码查询用户信息*/
	public Customer findCustomerByUserId(String customerUserId){
		return customerMapper.findCustomerByUserId(customerUserId);
	}
	/**根据客户识别码查询用户信息*/
	public Customer findCustomerByCustomerId(String customerId){
		return customerMapper.findCustomerByCustomerId(customerId);
	}
	/**添加用户*/
	public Integer addCustomer(Customer customer) {
		customer.setCustomerUpdateTime(new Date());
		return customerMapper.addCustomer(customer);
	}
	
	/**更新客户信息*/
	public Integer updateCustomer(Customer customer){
		customer.setCustomerUpdateTime(new Date());
		return customerMapper.updateCustomer(customer);
	}
	public List<Customer> getCustomer(QueryWebModel qwm){
		return customerMapper.getCustomer(qwm);
	}
}
