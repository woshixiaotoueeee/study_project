package org.jxau.lctoh.user.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.restaurant.domain.RestaurantCategory;

public interface RestaurantCategoryMapper {
	
	/**
	 * 根据分类识别码查询分类信息
	 * @param restaurantCategoryId
	 * @return
	 */
	public RestaurantCategory findRestaurantCategoryByRestaurantCategoryId(@Param("restaurantCategoryId")String restaurantCategoryId);
	
	
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
