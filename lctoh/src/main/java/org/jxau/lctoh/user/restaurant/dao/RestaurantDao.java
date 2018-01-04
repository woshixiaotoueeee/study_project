package org.jxau.lctoh.user.restaurant.dao;

import java.util.List;

import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.dish.mapper.DishCategoryMapper;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.mapper.RestaurantMapper;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("RestaurantDao")
public class RestaurantDao extends BaseDao {
	
	private RestaurantMapper restaurantMapper;
	private DishCategoryMapper dishCategoryMapper;
	
	public DishCategoryMapper getDishCategoryMapper() {
		return dishCategoryMapper;
	}
	public void setDishCategoryMapper(DishCategoryMapper dishCategoryMapper) {
		this.dishCategoryMapper = dishCategoryMapper;
	}
	public RestaurantMapper getRestaurantMapper() {
		return restaurantMapper;
	}
	public void setRestaurantMapper(RestaurantMapper restaurantMapper) {
		this.restaurantMapper = restaurantMapper;
	}
	@Override
	public void puttMapper() {
		restaurantMapper=this.getMapper(RestaurantMapper.class);
		dishCategoryMapper=this.getMapper(DishCategoryMapper.class);
	}
	
	
	/**
	 * 根据店家识别码查询店家信息
	 * @param restaurantId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByRestaurantId(String restaurantId){
		Restaurant restaurant=restaurantMapper.findRestaurantByRestaurantId(restaurantId);
		if(restaurant==null)return restaurant;
		return loadRestaurantDishCategory(restaurant);
	}
	
	/**
	 * 加载单个店家的菜肴分类信息
	 * @param restaurant
	 * @return
	 */
	private Restaurant loadRestaurantDishCategory(Restaurant restaurant) {
		restaurant.setDishCategory(dishCategoryMapper.findDishCategoryByRestaurantId(restaurant.getRestaurantId()));
		return restaurant;
	}
	/**
	 * 加载多个店家的菜肴分类信息
	 * @param restaurantList
	 * @return
	 */
	private List<Restaurant> loadRestaurantListDishCategory(List<Restaurant> restaurantList) {
		for(int i=0;i<restaurantList.size();i++){
			restaurantList.set(i, loadRestaurantDishCategory(restaurantList.get(i)));
		}
		return restaurantList;
	}
	/**
	 * 根据用户识别码查询店家信息
	 * @param userId
	 * @return Restaurant
	 */
	public Restaurant findRestaurantByUserId(String userId){
		Restaurant restaurant= restaurantMapper.findRestaurantByUserId(userId);
		if(restaurant==null)return restaurant;
		return loadRestaurantDishCategory(restaurant);
	}
	
	/**
	 * 根据分类及识别码及城市识别码查询店家信息
	 * @param restaurantCategoryId
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByRestaurantCategoryIdAndCityId(String restaurantCategoryId,String cityId){
		return restaurantMapper.findRestaurantByRestaurantCategoryIdAndCityId(restaurantCategoryId,cityId);
	}
	
	/**
	 * 根据城市识别码查询店家信息
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByCityId(String cityId){
		return restaurantMapper.findRestaurantByCityId(cityId);
	}
	
	
	/**
	 * 根据店名及城市识别码查询店家信息
	 * @param restaurantName
	 * @param cityId
	 * @return
	 */
	public List<Restaurant> findRestaurantByRestaurantNameAndCityId(String restaurantName,String cityId){
		return restaurantMapper.findRestaurantByRestaurantNameAndCityId(restaurantName, cityId);
	}
	

}
