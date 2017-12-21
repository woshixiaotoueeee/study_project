package org.jxau.lctoh.user.restaurant.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.position.province.domain.City;
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
	private Date restaurantUpdateTime;		//最近更新时间
	private State restaurantState;			//店家状态
	private User restaurantUser;			//对应用户
	private RestaurantCategory restaurantCategory;//所属分类
	private City restaurantCity;			//所属城市
	
	
	
	
	
}
