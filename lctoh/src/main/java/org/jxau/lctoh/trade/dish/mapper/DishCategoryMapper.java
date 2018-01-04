package org.jxau.lctoh.trade.dish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.trade.dish.domain.DishCategory;

/**
 * @author qdt_PC
 */
public interface DishCategoryMapper {
	
	/**
	 * 根据菜肴分类识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public DishCategory findDishCategoryById(@Param("dishCategoryId")String dishCategoryId);
	
	/**
	 * 根据店家识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public List<DishCategory> findDishCategoryByRestaurantId(@Param("restaurantId")String restaurantId);
	
}
