package org.jxau.lctoh.position.address.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.trade.order.domain.HarvestAddress;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户地址类
 * @author qdt_PC
 */
@Component
@Alias("Address")
@Scope("prototype")
public class Address {
	/**地址识别码*/
	private String addressId;
	/**地址联系人*/
	private String addressName;
	/**地址省份*/
	private String addressProvince;
	/**城市*/
	private String addressCity;
	/**地址详细信息*/
	private String addressInfo;	
	/**地址联系电话*/
	private String addressPhone;
	/**纬度*/
	private BigDecimal addressLatitude;
	/**经度*/
	private BigDecimal addressLongitude;
	/**最近更新时间*/
	private Date addressUpdateTime;
	/**
	 * 地址状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State addressState;
	/**
	 * 地址所属用户
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 * */
	private Customer addressCustomer;	
	public String getAddressId() {
		return addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public String getAddressProvince() {
		return addressProvince;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public String getAddressPhone() {
		return addressPhone;
	}
	public BigDecimal getAddressLatitude() {
		return addressLatitude;
	}
	public BigDecimal getAddressLongitude() {
		return addressLongitude;
	}
	public Date getAddressUpdateTime() {
		return addressUpdateTime;
	}
	public State getAddressState() {
		return addressState;
	}
	public Customer getAddressCustomer() {
		return addressCustomer;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	public void setAddressLatitude(BigDecimal addressLatitude) {
		this.addressLatitude = addressLatitude;
	}
	public void setAddressLongitude(BigDecimal addressLongitude) {
		this.addressLongitude = addressLongitude;
	}
	public void setAddressUpdateTime(Date addressUpdateTime) {
		this.addressUpdateTime = addressUpdateTime;
	}
	public void setAddressState(State addressState) {
		this.addressState = addressState;
	}
	public void setAddressCustomer(Customer addressCustomer) {
		this.addressCustomer = addressCustomer;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressName="
				+ addressName + ", addressProvince=" + addressProvince
				+ ", addressCity=" + addressCity + ", addressInfo="
				+ addressInfo + ", addressPhone=" + addressPhone
				+ ", addressLatitude=" + addressLatitude
				+ ", addressLongitude=" + addressLongitude
				+ ", addressUpdateTime=" + addressUpdateTime
				+ ", addressState=" + addressState + ", addressCustomer="
				+ addressCustomer + "]";
	}
	
	/**
	 * 无ID生成配送地址
	 * 生成的地址无ID
	 * @return
	 */
	public HarvestAddress toHarvestAddress(){
		HarvestAddress harvestAddress=new HarvestAddress();
		harvestAddress.setHarvestAddressName(addressName);
		harvestAddress.setHarvestAddressProvince(addressProvince);
		harvestAddress.setHarvestAddressCity(addressCity);
		harvestAddress.setHarvestAddressInfo(addressInfo);
		harvestAddress.setHarvestAddressPhone(addressPhone);
		harvestAddress.setHarvestAddressLatitude(addressLatitude);
		harvestAddress.setHarvestAddressLongitude(addressLongitude);
		return harvestAddress;
	}
	/**
	 * 有ID生成配送地址
	 * @param harvestAddressId
	 * @return
	 */
	public HarvestAddress toHarvestAddress(String harvestAddressId){
		HarvestAddress harvestAddress=this.toHarvestAddress();
		harvestAddress.setHarvestAddressId(harvestAddressId);
		return harvestAddress;
	}
	
}
