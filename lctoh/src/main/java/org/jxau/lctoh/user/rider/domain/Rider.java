package org.jxau.lctoh.user.rider.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
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
	private String riderId;			//骑手识别码
	private Date riderUpdateTime;	//最近更新时间
	private User riderUser;			//所属用户
	private BigDecimal riderLongitude;//经度
	private BigDecimal riderLatitude;//纬度
	private State riderState;		//状态
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
	@Override
	public String toString() {
		return "Rider [riderId=" + riderId + ", riderUpdateTime="
				+ riderUpdateTime + ", riderUser=" + riderUser
				+ ", riderLongitude=" + riderLongitude + ", riderLatitude="
				+ riderLatitude + ", riderState=" + riderState + "]";
	}
	
	
}
