package org.jxau.lctoh.user.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.restaurant.domain.RestaurantCategory;

public interface RestaurantCategoryMapper {
	
	/**
	 * 根据分类识别码查询分类信息
	 * @param id
	 * @return
	 */
	public RestaurantCategory findRestaurantCategoryById(@Param("id")String id);
	
	
	/**
	 * 根据分类名查询分类信息
	 * @param name
	 * @return List<RestaurantCategory>
	 */
	public List<RestaurantCategory> findRestaurantCategoryByName(@Param("name")String name);
	
	
	
	/**
	 * 根据分类状态查询分类信息
	 * @param stateId
	 * @return List<RestaurantCategory>
	 */
	public List<RestaurantCategory> findRestaurantCategoryByStateId(@Param("stateId")String stateId);
	
	
	
}
