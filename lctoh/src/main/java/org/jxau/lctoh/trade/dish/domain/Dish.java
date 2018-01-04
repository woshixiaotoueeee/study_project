package org.jxau.lctoh.trade.dish.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜肴
 * @author qdt_PC
 */
@Component
@Alias("Dish")
@Scope("prototype")
public class Dish {
	private String dishId;			//识别码
	private String dishName;		//菜肴名
	private BigDecimal  dishPrice;	//菜肴价格
	private String dishImage;		//菜肴图片
	private Date dishUpdateTime;	//最近更新时间
	private String dishIntro;		//菜肴见解
	private Integer dishQuantity;	//库存
	private State dishState;		//菜肴状态
	private DishCategory dishCategory;//所属菜肴分类
	public String getDishId() {
		return dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public BigDecimal getDishPrice() {
		return dishPrice;
	}
	
	public Date getDishUpdateTime() {
		return dishUpdateTime;
	}
	public String getDishIntro() {
		return dishIntro;
	}
	public Integer getDishQuantity() {
		return dishQuantity;
	}
	public State getDishState() {
		return dishState;
	}
	public DishCategory getDishCategory() {
		return dishCategory;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public void setDishPrice(BigDecimal dishPrice) {
		this.dishPrice = dishPrice;
	}
	
	public void setDishUpdateTime(Date dishUpdateTime) {
		this.dishUpdateTime = dishUpdateTime;
	}
	public void setDishIntro(String dishIntro) {
		this.dishIntro = dishIntro;
	}
	public void setDishQuantity(Integer dishQuantity) {
		this.dishQuantity = dishQuantity;
	}
	public void setDishState(State dishState) {
		this.dishState = dishState;
	}
	public void setDishCategory(DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}
	
	public String getDishImage() {
		return dishImage;
	}
	public void setDishImage(String dishImage) {
		this.dishImage = dishImage;
	}
	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishName=" + dishName
				+ ", dishPrice=" + dishPrice + ", dishImage=" + dishImage
				+ ", dishUpdateTime=" + dishUpdateTime + ", dishIntro="
				+ dishIntro + ", dishQuantity=" + dishQuantity + ", dishState="
				+ dishState + ", dishCategory=" + dishCategory + "]";
	}
	
}
