package org.jxau.lctoh.user.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;

/**
 * @author qdt_PC
 */
public interface RestaurantMapper {
	
	/**
	 * 根据店家识别码查询店家信息
	 * @param restaurantId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByRestaurantId(@Param("restaurantId")String restaurantId);
	
	/**
	 * 根据用户识别码查询店家信息
	 * @param userId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByUserId(@Param("userId")String userId);
	
	/**
	 * 根据分类及识别码及城市识别码查询店家信息
	 * @param restaurantCategoryId
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByRestaurantCategoryIdAndCityId(@Param("restaurantCategoryId")String restaurantCategoryId,@Param("cityId")String cityId);
	
	/**
	 * 根据城市识别码查询店家信息
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByCityId(@Param("cityId")String cityId);
	
	
	/**
	 * 根据店名及城市识别码查询店家信息
	 * @param restaurantName
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByRestaurantNameAndCityId(@Param("restaurantName")String restaurantName,@Param("cityId")String cityId);

	/**
	 * 更新店家信息
	 * @param restaurant
	 * @return
	 */
	public Integer updateRestaurant(Restaurant restaurant);
	
}
