package org.jxau.lctoh.position.location.domain;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 定位信息类
 * @author qdt_PC
 */
@Component
@Alias("Location")
@Scope("prototype")
public class Location {
	private String province;			//省份
	private String city;				//城市
	private String info;				//详细地址信息
	private BigDecimal longitude ;		//经度
	private BigDecimal latitude ;		//维度
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Location [province=" + province + ", city=" + city + ", info="
				+ info + ", longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
}
