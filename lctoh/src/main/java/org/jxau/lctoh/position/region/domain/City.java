package org.jxau.lctoh.position.region.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Alias("City")
@Scope("prototype")
public class City {
	private String cityId;		//城市识别码
	private String cityName;	//城市名
	private Date cityUpdateTime;//最近更新时间
	private State cityState;	//城市状态
	private Province cityProvince;//城市所属省份
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Date getCityUpdateTime() {
		return cityUpdateTime;
	}
	public void setCityUpdateTime(Date cityUpdateTime) {
		this.cityUpdateTime = cityUpdateTime;
	}
	public State getCityState() {
		return cityState;
	}
	public void setCityState(State cityState) {
		this.cityState = cityState;
	}
	public Province getCityProvince() {
		return cityProvince;
	}
	public void setCityProvince(Province cityProvince) {
		this.cityProvince = cityProvince;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName
				+ ", cityUpdateTime=" + cityUpdateTime + ", cityState="
				+ cityState + ", cityProvince=" + cityProvince + "]";
	}
}
