package org.jxau.lctoh.position.address.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.position.address.domain.Address;

/**
 * @author qdt_PC
 */
public interface AddressMapper {
	
	/**
	 * 根据地址识别码查询地址信息
	 * @param addressId
	 * @return Address
	 */
	public Address findAddressByAddressId(@Param("addressId")String addressId);
	
	/**
	 * 根据客户识别码查询地址信息
	 * @param customerId
	 * @return List<Address>
	 */
	public List<Address> findAddressByCustomerId(@Param("customerId")String customerId);
	
	/**
	 * 更新地址信息
	 * @param adrdess
	 * @return Integer
	 */
	public Integer updateAddress(Address address);
	
	
	/**
	 * 添加一个地址
	 * @param adrdess
	 * @return Integer
	 */
	public Integer addAddress(Address address);
	
	/**
	 * 删除一个地址
	 * @param address
	 * @return Integer
	 */
	public Integer deleteAddress(Address address);
	/**
	 * 根据用户及地址状态查询地址
	 * @param addressId
	 * @return Address
	 */
	public List<Address> findAddressByCustomerIdAndState(@Param("customerId")String customerId,@Param("addressStateId")Integer addressStateId);
	
	
}
