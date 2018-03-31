package org.jxau.lctoh.user.restaurant.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderTypeCount;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Alias("RestaurantWebModel")
@Scope("prototype")
public class RestaurantWebModel {
	
	private Restaurant restaurant;
	
	private List<OrderTypeCount> orderTypeCountList ;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public List<OrderTypeCount> getOrderTypeCountList() {
		return orderTypeCountList;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setOrderTypeCountList(List<OrderTypeCount> orderTypeCountList) {
		this.orderTypeCountList = orderTypeCountList;
	}
	
	
}
