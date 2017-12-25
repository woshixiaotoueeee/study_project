package org.jxau.lctoh.user.restaurant.dao;

import java.util.List;


import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.user.restaurant.domain.RestaurantCategory;
import org.jxau.lctoh.user.restaurant.mapper.RestaurantCategoryMapper;
import org.springframework.stereotype.Repository;
@Repository("RestaurantCategoryDao")
public class RestaurantCategoryDao extends BaseDao {
	private RestaurantCategoryMapper restaurantCategoryMapper;
	
	@Override
	public void puttMapper() {
		restaurantCategoryMapper=this.getMapper(RestaurantCategoryMapper.class);
	}
	public RestaurantCategoryMapper getRestaurantCategoryMapper() {
		return restaurantCategoryMapper;
	}
	public void setRestaurantCategoryMapper(
			RestaurantCategoryMapper restaurantCategoryMapper) {
		this.restaurantCategoryMapper = restaurantCategoryMapper;
	}
	
	

	/**
	 * 根据分类识别码查询分类信息
	 * @param id
	 * @return
	 */
	public RestaurantCategory findRestaurantCategoryById(String id){
		return restaurantCategoryMapper.findRestaurantCategoryById(id);
	}
	
	
	/**
	 * 根据分类名查询分类信息
	 * @param name
	 * @return List<RestaurantCategory>
	 */
	public List<RestaurantCategory> findRestaurantCategoryByName(String name){
		return restaurantCategoryMapper.findRestaurantCategoryByName(name);
	}
	
	
	
	/**
	 * 根据分类状态查询分类信息
	 * @param stateId
	 * @return List<RestaurantCategory>
	 */
	public List<RestaurantCategory> findRestaurantCategoryByStateId(String stateId){
		return restaurantCategoryMapper.findRestaurantCategoryByStateId(stateId);
	}
	

}
