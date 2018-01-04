package org.jxau.lctoh.trade.dish.service;

import java.util.List;


import org.jxau.lctoh.trade.dish.domain.Dish;
import org.springframework.stereotype.Service;
/**
 * @author qdt_PC
 */
@Service("DishService")
public class DishService {
	
	
	
	
	/**
	 * 根据菜肴识别码查询菜肴信息
	 * @param dishId
	 * @return Dish
	 */
	public Dish findDishByDishId(String dishId){
		return null;
		
	}
	/**
	 * 根据菜肴分类识别码查询菜肴信息
	 * @param dishCategoryId
	 * @return Dish
	 */
	public List<Dish> findDishByDishCategoryId(String dishCategoryId){
		return null;
	}
	
}
