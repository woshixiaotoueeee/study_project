package org.jxau.lctoh.user.customer.domain;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * 客户类
 * */
@Component
@Alias("Customer")
@Scope("prototype")
public class Customer{
	private User customerUser;					//所属用户
	private String customerId;			//客户识别码
	private String customerNickname;	//昵称
	private String customerPortrait;	//头像
	private String customerBalance;		//余额
	private String customerUpdateTime;	//最近更新时间
	private State customerState;		//状态信息
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNickname() {
		return customerNickname;
	}
	public void setCustomerNickname(String customerNickname) {
		this.customerNickname = customerNickname;
	}
	public String getCustomerPortrait() {
		return customerPortrait;
	}
	public void setCustomerPortrait(String customerPortrait) {
		this.customerPortrait = customerPortrait;
	}
	public String getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(String customerBalance) {
		this.customerBalance = customerBalance;
	}
	public String getCustomerUpdateTime() {
		return customerUpdateTime;
	}
	public void setCustomerUpdateTime(String customerUpdateTime) {
		this.customerUpdateTime = customerUpdateTime;
	}
	public State getCustomerState() {
		return customerState;
	}
	public void setCustomerState(State customerState) {
		this.customerState = customerState;
	}
	public User getCustomerUser() {
		return customerUser;
	}
	public void setCustomerUser(User customerUser) {
		this.customerUser = customerUser;
	}
	@Override
	public String toString() {
		return "Customer [customerUser=" + customerUser + ", customerId="
				+ customerId + ", customerNickname=" + customerNickname
				+ ", customerPortrait=" + customerPortrait
				+ ", customerBalance=" + customerBalance
				+ ", customerUpdateTime=" + customerUpdateTime
				+ ", customerState=" + customerState + "]";
	}
	
	

}
