package org.jxau.lctoh.user.restaurant.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 收藏店家类
 * @author qdt_PC
 */
@Component
@Alias("CollectRestaurant")
@Scope("prototype")
public class CollectRestaurant {
	/**
	 * 收藏者
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 * */
	private Customer collectCustomer;
	/**
	 * 收藏店家
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 * */
	private Restaurant collectRestaurant;
	/**最近更新时间*/
	private Date collectRestaurantUpdateTime;

	public Customer getCollectCustomer() {
		return collectCustomer;
	}

	public Restaurant getCollectRestaurant() {
		return collectRestaurant;
	}

	public Date getCollectRestaurantUpdateTime() {
		return collectRestaurantUpdateTime;
	}

	public void setCollectCustomer(Customer collectCustomer) {
		this.collectCustomer = collectCustomer;
	}

	public void setCollectRestaurant(Restaurant collectRestaurant) {
		this.collectRestaurant = collectRestaurant;
	}

	public void setCollectRestaurantUpdateTime(Date collectRestaurantUpdateTime) {
		this.collectRestaurantUpdateTime = collectRestaurantUpdateTime;
	}

	@Override
	public String toString() {
		return "CollectRestaurant [collectCustomer=" + collectCustomer
				+ ", collectRestaurant=" + collectRestaurant
				+ ", collectRestaurantUpdateTime="
				+ collectRestaurantUpdateTime + "]";
	}
}
