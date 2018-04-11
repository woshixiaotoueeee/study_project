package org.jxau.lctoh.user.restaurant.dao;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.datastatistics.orderstatistics.mapper.OrderStatisticsMapper;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.jxau.lctoh.trade.dish.mapper.DishCategoryMapper;
import org.jxau.lctoh.trade.dish.mapper.DishMapper;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.domain.RestaurantWebModel;
import org.jxau.lctoh.user.restaurant.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("RestaurantDao")
public class RestaurantDao extends BaseDao {
	@Autowired
	private RestaurantMapper restaurantMapper;
	@Autowired
	private DishCategoryMapper dishCategoryMapper;
	@Autowired
	private DishMapper dishMapper;
	public DishMapper getDishMapper() {
		return dishMapper;
	}
	public void setDishMapper(DishMapper dishMapper) {
		this.dishMapper = dishMapper;
	}


	@Autowired
	private OrderStatisticsMapper orderStatisticsMapper;
	public OrderStatisticsMapper getOrderStatisticsMapper() {
		return orderStatisticsMapper;
	}
	public void setOrderStatisticsMapper(OrderStatisticsMapper orderStatisticsMapper) {
		this.orderStatisticsMapper = orderStatisticsMapper;
	}
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
		dishMapper = this.getMapper(DishMapper.class);
		restaurantMapper=this.getMapper(RestaurantMapper.class);
		dishCategoryMapper=this.getMapper(DishCategoryMapper.class);
		orderStatisticsMapper=this.getMapper(OrderStatisticsMapper.class);
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
		List<DishCategory> dishCategoryList=dishCategoryMapper.findDishCategoryByRestaurantId(restaurant.getRestaurantId());
		restaurant.setDishCategory(loadDishCategoryListDish(dishCategoryList));
		return restaurant;
	}
	
	private List<DishCategory> loadDishCategoryListDish(
			List<DishCategory> dishCategoryList) {
		for(int i=0;i<dishCategoryList.size();i++){
			dishCategoryList.set(i, loadDishCategoryListDish(dishCategoryList.get(i)));
		}
		return dishCategoryList;
	}
	private DishCategory loadDishCategoryListDish(DishCategory dishCategory) {
		List<Dish> dishList=dishMapper.findDishByDishCategoryId(dishCategory.getDishCategoryId());
		dishCategory.setDishList(dishList);
		return dishCategory;
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
	/**更新店家信息
	 * @param restaurant
	 */
	public Integer updateRestaurant(Restaurant restaurant) {
		restaurant.setRestaurantUpdateTime(new Date());
		return restaurantMapper.updateRestaurant(restaurant);
	}
	/**
	 * 根据客户查询收藏的店家信息
	 * @param customerId
	 * @return
	 */
	public List<Restaurant> findCollectRestaurantByCustomerId(String customerId){
		return restaurantMapper.findCollectRestaurantByCustomerId(customerId);
	}
	
	
	/**
	 * 根据店家识别码查询店家大概信息
	 * @param restaurantId
	 * @return
	 */
	public RestaurantWebModel findRestaurantWebModelByRestaurantId(String restaurantId){
		RestaurantWebModel restaurantWebModel =new RestaurantWebModel();
		restaurantWebModel.setRestaurant(restaurantMapper.findRestaurantByRestaurantId(restaurantId));
		OrderStatisticsQureyModel orderStatisticsQureyModel=new OrderStatisticsQureyModel();
		orderStatisticsQureyModel.setRestaurantId(restaurantId);
		restaurantWebModel.setOrderTypeCountList(orderStatisticsMapper.orderStatisticsByState(orderStatisticsQureyModel));
		
		return restaurantWebModel;
	}
}
