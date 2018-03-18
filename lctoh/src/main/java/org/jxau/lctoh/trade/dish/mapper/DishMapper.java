package org.jxau.lctoh.trade.dish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.trade.dish.domain.Dish;

/**
 * @author qdt_PC
 */
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
	/**
	 * 根据客户识别码查询收藏菜肴信息
	 * @param customerId
	 * @return
	 */
	public List<Dish> findCollectDishByCustomerId(@Param("customerId")String customerId);
	
	/**
	 * 根据店家识别码查询菜肴信息
	 * @param restaurantId
	 * @return
	 */
	public List<Dish> findDishByRestaurantId(String restaurantId);
	
	
	/**
	 * 添加菜肴信息
	 * @param dish
	 * @return
	 */
	public Integer addDish(Dish dish);
	
	/**
	 * 删除菜肴信息
	 * @param dish
	 * @return
	 */
	public Integer deleteDish(Dish dish);
	/**
	 * 更新菜肴信息
	 * @param dish
	 * @return
	 */
	public Integer updateDish(Dish dish);
	
}
