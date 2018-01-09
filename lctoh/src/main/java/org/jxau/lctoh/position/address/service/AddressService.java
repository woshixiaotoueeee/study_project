package org.jxau.lctoh.position.address.service;


import java.util.List;

import org.jxau.lctoh.position.address.dao.AddressDao;
import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.state.dao.StateDao;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("AddressService")
public class AddressService{
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private StateDao stateDao;
	/**
	 * 根据地址识别码查询地址信息
	 * @param addressId
	 * @return Address
	 */
	public Address findAddressByAddressId(String addressId){
		return addressDao.findAddressByAddressId(addressId);
	}
	
	/**
	 * 根据客户识别码查询地址信息
	 * @param customerId
	 * @return List<Address>
	 */
	public List<Address> findAddressByCustomerId(String customerId){
		return addressDao.findAddressByCustomerId(customerId);
	}
	
	/**
	 * 更新地址信息
	 * @param adrdess
	 * @return Integer
	 */
	public Integer updateAddress(Address address){
		return addressDao.updateAddress(address);
	}
	/**
	 * 添加一个地址
	 * @param adrdess
	 * @return Integer
	 */
	public Integer addAddress(Address address){
		address.setAddressState(stateDao.findStateByStateId(60003));
		return addressDao.addAddress(address);
	}
	
	
	/**
	 * 删除一个地址
	 * @param adrdess
	 * @return Integer
	 */
	public Integer deleteAddress(String addressId){
		Address address=addressDao.findAddressByAddressId(addressId);
		return addressDao.deleteAddress(address);
	}

	
	
	
	/**
	 * 更新默认地址信息
	 * @param customer
	 * @param addressId
	 * @throws Exception 
	 */
	public void updateAddressState(Customer customer, String addressId) throws Exception {
		/**
		 * 先将所有用户的地址改为非默认地址
		 * 再将现在选中的地址改为默认
		 * */
		List<Address> addressList= addressDao.findAddressByCustomerIdAndState(customer.getCustomerId(), 60002);
		if(addressList==null||addressList.size()==0)throw new Exception();
		Address address=addressList.get(0);
		State state=address.getAddressState();
		state.setStateId(60003);
		address.setAddressState(state);
		addressDao.updateAddress(address);
		
		Address _address=addressDao.findAddressByAddressId(addressId);
		if(_address==null)throw new Exception();
		State _state=_address.getAddressState();
		_state.setStateId(60002);
		_address.setAddressState(_state);
		addressDao.updateAddress(_address);
	}
	
	

}
