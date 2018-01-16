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
	/**省份*/
	private String province;
	/**城市*/
	private String city;
	/**详细地址信息*/
	private String info;
	/**经度*/
	private BigDecimal longitude ;
	/**纬度*/
	private BigDecimal latitude ;
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
