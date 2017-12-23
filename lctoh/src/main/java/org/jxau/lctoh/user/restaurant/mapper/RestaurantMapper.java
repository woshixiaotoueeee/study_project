package org.jxau.lctoh.user.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.domain.RestaurantCategory;

public interface RestaurantMapper {
	
	/**
	 * 根据店家识别码查询店家信息
	 * @param restaurantId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByRestaurantId(@Param("restaurantId")String restaurantId);
	
	/**
	 * 根据店家识别码查询店家信息
	 * @param restaurantId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByRestaurantId(@Param("restaurantId")String restaurantId);
	
	/**
	 * 根据分类名查询分类信息
	 * @param restaurantCategoryName
	 * @return List<RestaurantCategory>
	 */
	public List<RestaurantCategory> findRestaurantCategoryByRestaurantCategoryName(@Param("restaurantCategoryName")String restaurantCategoryName);
	
	
	
	/**
	 * 根据分类状态查询分类信息
	 * @param restaurantCategoryStateId
	 * @return List<RestaurantCategory>
	 */
	public List<RestaurantCategory> findRestaurantCategoryByRestaurantCategoryStateId(@Param("restaurantCategoryStateId")String restaurantCategoryStateId);
	
	
	
}
