package org.jxau.lctoh.trade.dish.dao;

import java.util.List;


import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.jxau.lctoh.trade.dish.mapper.DishCategoryMapper;
import org.springframework.stereotype.Repository;

@Repository("DishCategoryDao")
public class DishCategoryDao  extends BaseDao {

	
	private DishCategoryMapper dishCategoryMapper;
	
	
	public DishCategoryMapper getDishCategoryMapper() {
		return dishCategoryMapper;
	}
	public void setDishCategoryMapper(DishCategoryMapper dishCategoryMapper) {
		this.dishCategoryMapper = dishCategoryMapper;
	}
	@Override
	public void puttMapper() {
		dishCategoryMapper=this.getMapper(DishCategoryMapper.class);
	}
	
	
	/**
	 * 根据菜肴分类识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public DishCategory findDishCategoryById(String dishCategoryId){
		DishCategory dishCategory=dishCategoryMapper.findDishCategoryById(dishCategoryId);
		
		/**菜肴*/
		if(dishCategory!=null){
			
		}
		
		return dishCategory;
	}
	
	/**
	 * 根据店家识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public List<DishCategory> findDishCategoryByRestaurantId(String restaurantId){
		return dishCategoryMapper.findDishCategoryByRestaurantId(restaurantId);
	}
	
}
