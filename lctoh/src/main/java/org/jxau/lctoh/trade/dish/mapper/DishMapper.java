package org.jxau.lctoh.trade.dish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.trade.dish.domain.DishCategory;

public interface DishMapper {
	/**
	 * 根据菜肴识别码查询菜肴信息
	 * @param dishId
	 * @return Dish
	 */
	public Dish findDishByDishId(@Param("dishId")String dishId);
	/**
	 * 根据菜肴分类识别码查询菜肴信息
	 * @param dishCategoryId
	 * @return Dish
	 */
	public List<Dish> findDishByDishCategoryId(@Param("dishCategoryId")String dishCategoryId);
	
}
