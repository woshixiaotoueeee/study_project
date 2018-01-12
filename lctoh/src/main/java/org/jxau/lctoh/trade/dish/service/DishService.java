package org.jxau.lctoh.trade.dish.service;

import java.util.List;





import org.jxau.lctoh.trade.dish.dao.DishDao;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author qdt_PC
 */
@Service("DishService")
public class DishService {
	@Autowired
	private DishDao dishDao;
	
	
	
	/**
	 * 根据菜肴识别码查询菜肴信息
	 * @param dishId
	 * @return Dish
	 */
	public Dish findDishByDishId(String dishId){
		return dishDao.findDishByDishId(dishId);
		
	}
	/**
	 * 根据菜肴分类识别码查询菜肴信息
	 * @param dishCategoryId
	 * @return List<Dish>
	 */
	public List<Dish> findDishByDishCategoryId(String dishCategoryId){
		return dishDao.findDishByDishCategoryId(dishCategoryId);
	}
	
	
	
	/**
	 * 根据店家ID查询菜肴信息
	 * @param restaurantId
	 * @return List<Dish>
	 */
	public List<Dish> findDishByRestaurantId(String restaurantId) {
		return null;
	}
	
}
