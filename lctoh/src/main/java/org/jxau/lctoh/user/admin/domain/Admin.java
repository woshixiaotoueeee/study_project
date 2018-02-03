package org.jxau.lctoh.user.admin.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 管理员类
 * @author qdt_PC
 */
@Component
@Alias("Admin")
@Scope("prototype")
public class Admin {
	/**
	 * 用户基础信息
	 * @see org.jxau.lctoh.user.basis.domain.User
	 * */
	private User adminUser;
	/**管理员识别码*/
	private String adminId;
	/**最近更新时间*/
	private Date adminUpdateTime;
	/**管理员名字*/
	private String adminName;
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	/**
	 * 管理员状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State adminState;
	public User getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public Date getAdminUpdateTime() {
		return adminUpdateTime;
	}
	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}
	public State getAdminState() {
		return adminState;
	}
	public void setAdminState(State adminState) {
		this.adminState = adminState;
	}
	@Override
	public String toString() {
		return "Admin [adminUser=" + adminUser + ", adminId=" + adminId
				+ ", adminUpdateTime=" + adminUpdateTime + ", adminName="
				+ adminName + ", adminState=" + adminState + "]";
	}
	
}
