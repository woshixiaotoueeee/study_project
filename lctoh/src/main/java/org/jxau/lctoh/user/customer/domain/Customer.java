package org.jxau.lctoh.user.customer.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 客户类
 * @author qdt_PC
 */
@Component
@Alias("Customer")
@Scope("prototype")
public class Customer{
	/**
	 * 所属用户
	 * @see org.jxau.lctoh.user.basis.domain.User
	 * */
	private User customerUser;
	/**客户识别码*/
	private String customerId;
	/**昵称*/
	private String customerNickname;
	/**头像*/
	private String customerPortrait;
	/**余额*/
	private BigDecimal customerBalance;
	/**最近更新时间*/
	private Date customerUpdateTime;
	/**
	 * 状态信息
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State customerState;
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
	public BigDecimal getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(BigDecimal customerBalance) {
		this.customerBalance = customerBalance;
	}
	public Date getCustomerUpdateTime() {
		return customerUpdateTime;
	}
	public void setCustomerUpdateTime(Date customerUpdateTime) {
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
	
	public Customer(){}
	
}
