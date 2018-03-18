package org.jxau.lctoh.trade.dish.service;

import java.util.List;


import org.jxau.lctoh.trade.dish.dao.DishCategoryDao;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qdt_PC
 */
@Service("DishCategoryService")
public class DishCategoryService {
	@Autowired
	private DishCategoryDao dishCategoryDao;
	
	
	
	
	/**
	 * 根据菜肴分类识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public DishCategory findDishCategoryById(String dishCategoryId){
		return dishCategoryDao.findDishCategoryById(dishCategoryId);
	}
	
	/**
	 * 根据店家识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public List<DishCategory> findDishCategoryByRestaurantId(String restaurantId){
		return dishCategoryDao.findDishCategoryByRestaurantId(restaurantId);
	}
	
	
	
	/**
	 * 添加菜肴分类信息
	 * @param dishCategory
	 * @return
	 */
	public Integer addDishCategory(DishCategory dishCategory){
		return dishCategoryDao.addDishCategory(dishCategory);
	}
	/**
	 * 删除菜肴分类信息
	 * @param dishCategory
	 * @return
	 */
	public Integer deleteDishCategory(DishCategory dishCategory){
		return dishCategoryDao.deleteDishCategory(dishCategory);
	}
	/**
	 * 修改菜肴分类信息
	 * @param dishCategory
	 * @return
	 */
	public Integer updateDishCategory(DishCategory dishCategory){
		DishCategory _dishCategory=dishCategoryDao.findDishCategoryById(dishCategory.getDishCategoryId());
		_dishCategory.setDishCategoryName(dishCategory.getDishCategoryName());
		return dishCategoryDao.updateDishCategory(_dishCategory);
	}
}
