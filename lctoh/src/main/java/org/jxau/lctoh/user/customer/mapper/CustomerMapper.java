package org.jxau.lctoh.user.customer.mapper;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.customer.domain.Customer;


/**
 * 客户类代理接口
 * */
public interface CustomerMapper {
	/**根据用户识别码查询用户信息*/
	public Customer findCustomerByUserId(@Param("customerUserId")String customerUserId);
	/**根据客户识别码查询用户信息*/
	public Customer findCustomerByCustomerId(@Param("customerId")String customerId);
	
}