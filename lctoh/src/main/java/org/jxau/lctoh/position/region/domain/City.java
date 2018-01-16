package org.jxau.lctoh.position.region.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 城市信息类
 * @author qdt_PC
 */
@Component
@Alias("City")
@Scope("prototype")
public class City {
	/**城市识别码*/
	private String cityId;
	/**城市名*/
	private String cityName;
	/**最近更新时间*/
	private Date cityUpdateTime;
	/**
	 * 城市状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State cityState;
	/**
	 * 城市所属省份
	 * @see org.jxau.lctoh.position.region.domain.Province
	 * */
	private Province cityProvince;
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
