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
	private User adminUser;
	private String adminId;
	private Date adminUpdateTime;
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
				+ ", adminUpdateTime=" + adminUpdateTime + ", adminState="
				+ adminState + "]";
	}
	
	
	
}
