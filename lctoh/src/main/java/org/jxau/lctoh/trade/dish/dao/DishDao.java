package org.jxau.lctoh.trade.dish.dao;

import java.util.List;


import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.trade.dish.mapper.DishMapper;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("DishDao")
public class DishDao extends BaseDao {
	private DishMapper dishMapper;
	public DishMapper getDishMapper() {
		return dishMapper;
	}
	public void setDishMapper(DishMapper dishMapper) {
		this.dishMapper = dishMapper;
	}
	@Override
	public void puttMapper() {
		dishMapper=getMapper(DishMapper.class);
	}
	
	/**
	 * 根据菜肴识别码查询菜肴信息
	 * @param dishId
	 * @return Dish
	 */
	public Dish findDishByDishId(String dishId){
		return dishMapper.findDishByDishId(dishId);
	}
	/**
	 * 根据菜肴分类识别码查询菜肴信息
	 * @param dishCategoryId
	 * @return Dish
	 */
	public List<Dish> findDishByDishCategoryId(String dishCategoryId){
		return dishMapper.findDishByDishCategoryId(dishCategoryId);
	}
	
}
