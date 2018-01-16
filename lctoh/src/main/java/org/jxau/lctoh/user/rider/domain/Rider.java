package org.jxau.lctoh.user.rider.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 骑手
 * @author qdt_PC
 */
@Component
@Alias("Rider")
@Scope("prototype")
public class Rider {
	/**骑手识别码*/
	private String riderId;
	/**骑手姓名*/
	private String riderName;
	/**最近更新时间*/
	private Date riderUpdateTime;
	/**
	 * 用户基础信息
	 * @see org.jxau.lctoh.user.basis.domain.User
	 * */
	private User riderUser;
	/**
	 * 所属区域
	 * @see org.jxau.lctoh.position.region.domain.City
	 * */
	private City riderCity;	
	/**经度*/
	private BigDecimal riderLongitude;
	/**纬度*/
	private BigDecimal riderLatitude;
	/**
	 * 状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State riderState;
	public String getRiderId() {
		return riderId;
	}
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	public Date getRiderUpdateTime() {
		return riderUpdateTime;
	}
	public void setRiderUpdateTime(Date riderUpdateTime) {
		this.riderUpdateTime = riderUpdateTime;
	}
	public User getRiderUser() {
		return riderUser;
	}
	public void setRiderUser(User riderUser) {
		this.riderUser = riderUser;
	}
	public BigDecimal getRiderLongitude() {
		return riderLongitude;
	}
	public void setRiderLongitude(BigDecimal riderLongitude) {
		this.riderLongitude = riderLongitude;
	}
	public BigDecimal getRiderLatitude() {
		return riderLatitude;
	}
	public void setRiderLatitude(BigDecimal riderLatitude) {
		this.riderLatitude = riderLatitude;
	}
	public State getRiderState() {
		return riderState;
	}
	public void setRiderState(State riderState) {
		this.riderState = riderState;
	}
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	
	public City getRiderCity() {
		return riderCity;
	}
	public void setRiderCity(City riderCity) {
		this.riderCity = riderCity;
	}
	@Override
	public String toString() {
		return "Rider [riderId=" + riderId + ", riderName=" + riderName
				+ ", riderUpdateTime=" + riderUpdateTime + ", riderUser="
				+ riderUser + ", riderCity=" + riderCity + ", riderLongitude="
				+ riderLongitude + ", riderLatitude=" + riderLatitude
				+ ", riderState=" + riderState + "]";
	}
	
	
}
