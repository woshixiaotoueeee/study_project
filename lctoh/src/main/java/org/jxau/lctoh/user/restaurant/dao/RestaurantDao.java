package org.jxau.lctoh.user.restaurant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.mapper.RestaurantMapper;
import org.springframework.stereotype.Repository;
@Repository("RestaurantDao")
public class RestaurantDao extends BaseDao {
	
	private RestaurantMapper restaurantMapper;
	
	public RestaurantMapper getRestaurantMapper() {
		return restaurantMapper;
	}
	public void setRestaurantMapper(RestaurantMapper restaurantMapper) {
		this.restaurantMapper = restaurantMapper;
	}
	@Override
	public void puttMapper() {
		restaurantMapper=this.getMapper(RestaurantMapper.class);
	}
	
	
	/**
	 * 根据店家识别码查询店家信息
	 * @param restaurantId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByRestaurantId(String restaurantId){
		Restaurant restaurant=restaurantMapper.findRestaurantByRestaurantId(restaurantId);
		/**
		 * 设置菜肴分类信息
		 * */
		
		return restaurant;
	}
	
	/**
	 * 根据用户识别码查询店家信息
	 * @param userId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByUserId(String userId){
		Restaurant restaurant= restaurantMapper.findRestaurantByUserId(userId);
		/**
		 * 设置菜肴分类信息
		 * */
		return restaurant;
	}
	
	/**
	 * 根据分类及识别码及城市识别码查询店家信息
	 * @param restaurantCategoryId
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByRestaurantCategoryIdAndCityId(String restaurantCategoryId,String cityId){
		return restaurantMapper.findRestaurantByRestaurantCategoryIdAndCityId(restaurantCategoryId,cityId);
	}
	
	/**
	 * 根据城市识别码查询店家信息
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByCityId(String cityId){
		return restaurantMapper.findRestaurantByCityId(cityId);
	}
	
	
	/**
	 * 根据店名及城市识别码查询店家信息
	 * @param restaurantName
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByRestaurantNameAndCityId(String restaurantName,String cityId){
		return restaurantMapper.findRestaurantByRestaurantNameAndCityId(restaurantName, cityId);
	}
	
	
	
	
	

}
