package org.jxau.lctoh.trade.dish.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜肴分类
 * @author qdt_PC
 */
@Component
@Alias("DishCategory")
@Scope("prototype")
public class DishCategory {
	/**菜肴分类ID */
	private String dishCategoryId;
	/**菜肴分类名 */
	private String dishCategoryName;
	/**
	 * 分类状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State dishCategoryState;
	/**最近更新时间 */
	private Date dishCategoryUpdateTime;
	/**
	 * 所属店家
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 * */
	private Restaurant dishCategoryRestaurant;
	/**
	 * 分类所拥有的菜肴
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 * */
	private List<Dish> dishList;
	public String getDishCategoryId() {
		return dishCategoryId;
	}
	public String getDishCategoryName() {
		return dishCategoryName;
	}
	public State getDishCategoryState() {
		return dishCategoryState;
	}
	public Date getDishCategoryUpdateTime() {
		return dishCategoryUpdateTime;
	}
	public Restaurant getDishCategoryRestaurant() {
		return dishCategoryRestaurant;
	}
	public List<Dish> getDishList() {
		return dishList;
	}
	public void setDishCategoryId(String dishCategoryId) {
		this.dishCategoryId = dishCategoryId;
	}
	public void setDishCategoryName(String dishCategoryName) {
		this.dishCategoryName = dishCategoryName;
	}
	public void setDishCategoryState(State dishCategoryState) {
		this.dishCategoryState = dishCategoryState;
	}
	public void setDishCategoryUpdateTime(Date dishCategoryUpdateTime) {
		this.dishCategoryUpdateTime = dishCategoryUpdateTime;
	}
	public void setDishCategoryRestaurant(Restaurant dishCategoryRestaurant) {
		this.dishCategoryRestaurant = dishCategoryRestaurant;
	}
	public void setDishList(List<Dish> dishList) {
		this.dishList = dishList;
	}
	@Override
	public String toString() {
		return "DishCategory [dishCategoryId=" + dishCategoryId
				+ ", dishCategoryName=" + dishCategoryName
				+ ", dishCategoryState=" + dishCategoryState
				+ ", dishCategoryUpdateTime=" + dishCategoryUpdateTime
				+ ", dishCategoryRestaurant=" + dishCategoryRestaurant
				+ ", dishList=" + dishList + "]";
	}
	
}
