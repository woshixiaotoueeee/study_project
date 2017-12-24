package org.jxau.lctoh.user.restaurant.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Alias("Restaurant")
@Scope("prototype")
public class Restaurant {
	private String restaurantId;			//店家识别码
	private String restaurantName;			//店名
	private String restaurantPhone;			//店家联系方式
	private String restaurantImage;			//店家图片
	private BigDecimal restaurantLongitude;	//经度
	private BigDecimal restaurantLatitude;	//经度
	private String restaurantNotice;		//公告
	private BigDecimal restaurantDistance;	//距离
	private BigDecimal restaurantDeliveryFee;//配送费
	private Date restaurantUpdateTime;		//最近更新时间
	private State restaurantState;			//店家状态
	private User restaurantUser;			//对应用户
	private RestaurantCategory restaurantCategory;//所属分类
	private City restaurantCity;			//所属城市
	public String getRestaurantId() {
		return restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public String getRestaurantPhone() {
		return restaurantPhone;
	}
	public String getRestaurantImage() {
		return restaurantImage;
	}
	public BigDecimal getRestaurantLongitude() {
		return restaurantLongitude;
	}
	public BigDecimal getRestaurantLatitude() {
		return restaurantLatitude;
	}
	public String getRestaurantNotice() {
		return restaurantNotice;
	}
	public BigDecimal getRestaurantDistance() {
		return restaurantDistance;
	}
	public Date getRestaurantUpdateTime() {
		return restaurantUpdateTime;
	}
	public State getRestaurantState() {
		return restaurantState;
	}
	public User getRestaurantUser() {
		return restaurantUser;
	}
	public RestaurantCategory getRestaurantCategory() {
		return restaurantCategory;
	}
	public City getRestaurantCity() {
		return restaurantCity;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}
	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}
	public void setRestaurantLongitude(BigDecimal restaurantLongitude) {
		this.restaurantLongitude = restaurantLongitude;
	}
	public void setRestaurantLatitude(BigDecimal restaurantLatitude) {
		this.restaurantLatitude = restaurantLatitude;
	}
	public void setRestaurantNotice(String restaurantNotice) {
		this.restaurantNotice = restaurantNotice;
	}
	public void setRestaurantDistance(BigDecimal restaurantDistance) {
		this.restaurantDistance = restaurantDistance;
	}
	public void setRestaurantUpdateTime(Date restaurantUpdateTime) {
		this.restaurantUpdateTime = restaurantUpdateTime;
	}
	public void setRestaurantState(State restaurantState) {
		this.restaurantState = restaurantState;
	}
	public void setRestaurantUser(User restaurantUser) {
		this.restaurantUser = restaurantUser;
	}
	public void setRestaurantCategory(RestaurantCategory restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}
	public void setRestaurantCity(City restaurantCity) {
		this.restaurantCity = restaurantCity;
	}
	public BigDecimal getRestaurantDeliveryFee() {
		return restaurantDeliveryFee;
	}
	public void setRestaurantDeliveryFee(BigDecimal restaurantDeliveryFee) {
		this.restaurantDeliveryFee = restaurantDeliveryFee;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName="
				+ restaurantName + ", restaurantPhone=" + restaurantPhone
				+ ", restaurantImage=" + restaurantImage
				+ ", restaurantLongitude=" + restaurantLongitude
				+ ", restaurantLatitude=" + restaurantLatitude
				+ ", restaurantNotice=" + restaurantNotice
				+ ", restaurantDistance=" + restaurantDistance
				+ ", restaurantDeliveryFee=" + restaurantDeliveryFee
				+ ", restaurantUpdateTime=" + restaurantUpdateTime
				+ ", restaurantState=" + restaurantState + ", restaurantUser="
				+ restaurantUser + ", restaurantCategory=" + restaurantCategory
				+ ", restaurantCity=" + restaurantCity + "]";
	}
}
