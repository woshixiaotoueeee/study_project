package org.jxau.lctoh.user.restaurant.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 店家分类实体类
 * @author qdt_PC
 */
@Component
@Alias("RestaurantCategory")
@Scope("prototype")
public class RestaurantCategory {
	private String restaurantCategoryId;		//分类识别码
	private String restaurantCategoryName;		//分类名
	private Date restaurantCategoryUpdateTime;	//最近更新日期
	private State restaurantCategoryState;		//分类状态
	public String getRestaurantCategoryId() {
		return restaurantCategoryId;
	}
	public void setRestaurantCategoryId(String restaurantCategoryId) {
		this.restaurantCategoryId = restaurantCategoryId;
	}
	public String getRestaurantCategoryName() {
		return restaurantCategoryName;
	}
	public void setRestaurantCategoryName(String restaurantCategoryName) {
		this.restaurantCategoryName = restaurantCategoryName;
	}
	public Date getRestaurantCategoryUpdateTime() {
		return restaurantCategoryUpdateTime;
	}
	public void setRestaurantCategoryUpdateTime(Date restaurantCategoryUpdateTime) {
		this.restaurantCategoryUpdateTime = restaurantCategoryUpdateTime;
	}
	public State getRestaurantCategoryState() {
		return restaurantCategoryState;
	}
	public void setRestaurantCategoryState(State restaurantCategoryState) {
		this.restaurantCategoryState = restaurantCategoryState;
	}
	@Override
	public String toString() {
		return "RestaurantCategory [restaurantCategoryId="
				+ restaurantCategoryId + ", restaurantCategoryName="
				+ restaurantCategoryName + ", restaurantCategoryUpdateTime="
				+ restaurantCategoryUpdateTime + ", restaurantCategoryState="
				+ restaurantCategoryState + "]";
	}
}
