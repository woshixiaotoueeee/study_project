package org.jxau.lctoh.trade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户地址类
 * @author qdt_PC
 */
@Component
@Alias("HarvestAddress")
@Scope("prototype")
public class HarvestAddress {
	private String harvestAddressId;			//地址识别码
	private String harvestAddressName;			//地址联系人
	private String harvestAddressProvince;		//地址省份
	private String harvestAddressCity;			//城市
	private String harvestAddressInfo;			//地址详细信息
	private String harvestAddressPhone;		//地址联系电话
	private BigDecimal harvestAddressLatitude;	//纬度
	private BigDecimal harvestAddressLongitude;//经度
	private Date harvestAddressUpdateTime;		//最近更新时间

	public String getHarvestAddressId() {
		return harvestAddressId;
	}
	public void setHarvestAddressId(String harvestAddressId) {
		this.harvestAddressId = harvestAddressId;
	}
	public String getHarvestAddressName() {
		return harvestAddressName;
	}
	public void setHarvestAddressName(String harvestAddressName) {
		this.harvestAddressName = harvestAddressName;
	}
	public String getHarvestAddressProvince() {
		return harvestAddressProvince;
	}
	public void setHarvestAddressProvince(String harvestAddressProvince) {
		this.harvestAddressProvince = harvestAddressProvince;
	}
	public String getHarvestAddressCity() {
		return harvestAddressCity;
	}
	public void setHarvestAddressCity(String harvestAddressCity) {
		this.harvestAddressCity = harvestAddressCity;
	}
	public String getHarvestAddressInfo() {
		return harvestAddressInfo;
	}
	public void setHarvestAddressInfo(String harvestAddressInfo) {
		this.harvestAddressInfo = harvestAddressInfo;
	}
	public String getHarvestAddressPhone() {
		return harvestAddressPhone;
	}
	public void setHarvestAddressPhone(String harvestAddressPhone) {
		this.harvestAddressPhone = harvestAddressPhone;
	}
	public BigDecimal getHarvestAddressLatitude() {
		return harvestAddressLatitude;
	}
	public void setHarvestAddressLatitude(BigDecimal harvestAddressLatitude) {
		this.harvestAddressLatitude = harvestAddressLatitude;
	}
	public BigDecimal getHarvestAddressLongitude() {
		return harvestAddressLongitude;
	}
	public void setHarvestAddressLongitude(BigDecimal harvestAddressLongitude) {
		this.harvestAddressLongitude = harvestAddressLongitude;
	}
	public Date getHarvestAddressUpdateTime() {
		return harvestAddressUpdateTime;
	}
	public void setHarvestAddressUpdateTime(Date harvestAddressUpdateTime) {
		this.harvestAddressUpdateTime = harvestAddressUpdateTime;
	}
	
	@Override
	public String toString() {
		return "HarvestAddress [harvestAddressId=" + harvestAddressId
				+ ", harvestAddressName=" + harvestAddressName
				+ ", harvestAddressProvince=" + harvestAddressProvince
				+ ", harvestAddressCity=" + harvestAddressCity
				+ ", harvestAddressInfo=" + harvestAddressInfo
				+ ", harvestAddressPhone=" + harvestAddressPhone
				+ ", harvestAddressLatitude=" + harvestAddressLatitude
				+ ", harvestAddressLongitude=" + harvestAddressLongitude
				+ ", harvestAddressUpdateTime=" + harvestAddressUpdateTime+"]";
	}
	
}
