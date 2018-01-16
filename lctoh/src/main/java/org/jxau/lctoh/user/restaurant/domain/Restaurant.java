package org.jxau.lctoh.user.restaurant.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 店家信息实体类
 * @author qdt_PC
 */
@Component
@Alias("Restaurant")
@Scope("prototype")
public class Restaurant {
	/**店家识别码*/
	private String restaurantId;
	/**店名*/
	private String restaurantName;
	/**店家联系方式*/
	private String restaurantPhone;	
	/**店家图片*/
	private String restaurantImage;
	/**经度*/
	private BigDecimal restaurantLongitude;
	/**纬度*/
	private BigDecimal restaurantLatitude;
	/**店铺地址*/
	private String restaurantAddressInfo;
	/**公告*/
	private String restaurantNotice;
	/**距离*/
	private BigDecimal restaurantDistance;
	/**配送费*/
	private BigDecimal restaurantDeliveryFee;
	/**最近更新时间*/
	private Date restaurantUpdateTime;
	/**
	 * 状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State restaurantState;
	/**
	 * 用户基础信息
	 * @see org.jxau.lctoh.user.basis.domain.User
	 * */
	private User restaurantUser;
	/**
	 * 所属分类
	 * @see org.jxau.lctoh.user.restaurant.domain.RestaurantCategory
	 * */
	private RestaurantCategory restaurantCategory;
	/**
	 * 所属城市
	 * @see  org.jxau.lctoh.position.region.domain.City
	 * */
	private City restaurantCity;
	/**
	 * 拥有的菜肴分类
	 * @see org.jxau.lctoh.trade.dish.domain.DishCategory
	 * */
	private List<DishCategory> dishCategory;
	
	public String getRestaurantAddressInfo() {
		return restaurantAddressInfo;
	}
	public void setRestaurantAddressInfo(String restaurantAddressInfo) {
		this.restaurantAddressInfo = restaurantAddressInfo;
	}
	public List<DishCategory> getDishCategory() {
		return dishCategory;
	}
	public void setDishCategory(List<DishCategory> dishCategory) {
		this.dishCategory = dishCategory;
	}
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
				+ ", restaurantAddressInfo=" + restaurantAddressInfo
				+ ", restaurantNotice=" + restaurantNotice
				+ ", restaurantDistance=" + restaurantDistance
				+ ", restaurantDeliveryFee=" + restaurantDeliveryFee
				+ ", restaurantUpdateTime=" + restaurantUpdateTime
				+ ", restaurantState=" + restaurantState + ", restaurantUser="
				+ restaurantUser + ", restaurantCategory=" + restaurantCategory
				+ ", restaurantCity=" + restaurantCity + ", dishCategory="
				+ dishCategory + "]";
	}
	
	
}
