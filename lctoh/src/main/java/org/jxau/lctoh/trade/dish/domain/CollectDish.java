package org.jxau.lctoh.trade.dish.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜肴收藏
 * @author qdt_PC
 */
@Component
@Alias("CollectDish")
@Scope("prototype")
public class CollectDish {
	/**
	 * 收藏者
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 * */
	private Customer collectCustomer;
	/**
	 * 收藏的菜肴
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 * */
	private Dish collectDish;
	/**最近更新时间*/
	private Date collectDishUpdateTime;
	public Customer getCollectCustomer() {
		return collectCustomer;
	}
	public void setCollectCustomer(Customer collectCustomer) {
		this.collectCustomer = collectCustomer;
	}
	public Dish getCollectDish() {
		return collectDish;
	}
	public void setCollectDish(Dish collectDish) {
		this.collectDish = collectDish;
	}
	public Date getCollectDishUpdateTime() {
		return collectDishUpdateTime;
	}
	public void setCollectDishUpdateTime(Date collectDishUpdateTime) {
		this.collectDishUpdateTime = collectDishUpdateTime;
	}
	@Override
	public String toString() {
		return "CollectDish [collectCustomer=" + collectCustomer
				+ ", collectDish=" + collectDish + ", collectDishUpdateTime="
				+ collectDishUpdateTime + "]";
	}
	
}
