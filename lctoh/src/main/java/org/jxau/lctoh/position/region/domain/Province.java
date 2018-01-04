package org.jxau.lctoh.position.region.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 省份信息类
 * @author qdt_PC
 */
@Component
@Alias("Province")
@Scope("prototype")
public class Province {
	private String provinceId;		//省份识别码
	private String provinceName;	//省名
	private Date provinceUpdateTime;//最近更新时间
	private State provinceState;	//省份状态
	private List<City> cityList;	//省份城市
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Date getProvinceUpdateTime() {
		return provinceUpdateTime;
	}
	public void setProvinceUpdateTime(Date provinceUpdateTime) {
		this.provinceUpdateTime = provinceUpdateTime;
	}
	public State getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(State provinceState) {
		this.provinceState = provinceState;
	}
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	@Override
	public String toString() {
		return "Province [provinceId=" + provinceId + ", provinceName="
				+ provinceName + ", provinceUpdateTime=" + provinceUpdateTime
				+ ", provinceState=" + provinceState + ", cityList=" + cityList
				+ "]";
	}
	
}
