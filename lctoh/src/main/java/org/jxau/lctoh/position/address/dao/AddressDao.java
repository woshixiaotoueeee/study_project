package org.jxau.lctoh.position.address.dao;

import java.util.Date;
import java.util.List;




import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.position.address.mapper.AddressMapper;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("AddressDao")
public class AddressDao extends BaseDao{
	
	private AddressMapper addressMapper;
	
	public AddressMapper getAddressMapper() {
		return addressMapper;
	}
	public void setAddressMapper(AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}
	@Override
	public void puttMapper() {
		addressMapper=getMapper(AddressMapper.class);
	}
	
	/**
	 * 根据地址识别码查询地址信息
	 * @param addressId
	 * @return Address
	 */
	public Address findAddressByAddressId(String addressId){
		return addressMapper.findAddressByAddressId(addressId);
	}
	
	/**
	 * 根据客户识别码查询地址信息
	 * @param customerId
	 * @return List<Address>
	 */
	public List<Address> findAddressByCustomerId(String customerId){
		return addressMapper.findAddressByCustomerId(customerId);
	}
	
	/**
	 * 更新地址信息
	 * @param adrdess
	 * @return Integer
	 */
	public Integer updateAddress(Address address){
		address.setAddressUpdateTime(new Date());
		return addressMapper.updateAddress(address);
	}
	/**
	 * 添加一个地址
	 * @param adrdess
	 * @return Integer
	 */
	public Integer addAddress(Address address){
		address.setAddressUpdateTime(new Date());
		return addressMapper.addAddress(address);
	}

}
