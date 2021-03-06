package org.jxau.lctoh.user.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.admin.domain.QueryWebModel;
import org.jxau.lctoh.user.customer.domain.Customer;

/**
 * 客户类代理接口
 * @author qdt_PC
 */
public interface CustomerMapper {
	/**根据用户识别码查询用户信息*/
	public Customer findCustomerByUserId(@Param("customerUserId")String customerUserId);
	/**根据客户识别码查询用户信息*/
	public Customer findCustomerByCustomerId(@Param("customerId")String customerId);
	/**添加客户*/
	public Integer addCustomer(Customer customer);
	
	/**更新客户信息*/
	public Integer updateCustomer(Customer customer);
	
	public List<Customer> getCustomer(QueryWebModel qwm);
	
}
