package org.jxau.lctoh.trade.dish.service;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.trade.dish.dao.CollectDishDao;
import org.jxau.lctoh.trade.dish.dao.DishDao;
import org.jxau.lctoh.trade.dish.domain.CollectDish;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author qdt_PC
 */
@Service("DishService")
public class DishService {
	@Autowired
	private DishDao dishDao;
	@Autowired
	private CollectDishDao collectDishDao;
	
	
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
	 * 根据客户查询收藏的菜肴
	 * @param customer
	 * @return
	 */
	public List<Dish> findCollectDishByCustomer(Customer customer){
		return dishDao.findCollectDishByCustomerId(customer.getCustomerId());
	}
	
	
	
	/**
	 * 添加收藏的菜肴
	 * @param collectDish
	 * @return
	 */
	public Integer addCollectDish(Customer customer,String dishId){
		CollectDish collectDish =new CollectDish();
		collectDish.setCollectCustomer(customer);
		Dish dish=new Dish();
		dish.setDishId(dishId);;
		collectDish.setCollectDish(dish);
		return collectDishDao.addCollectDish(collectDish);
	}
	/**
	 * 删除收藏的菜肴
	 * @param collectDish
	 * @return
	 */
	public Integer deleteCollectDish(Customer customer,String dishId){
		CollectDish collectDish =new CollectDish();
		collectDish.setCollectCustomer(customer);
		Dish dish=new Dish();
		dish.setDishId(dishId);;
		collectDish.setCollectDish(dish);
		return collectDishDao.deleteCollectDish(collectDish);
	}
	
	/**
	 * 根据店家ID查询菜肴信息
	 * @param restaurantId
	 * @return List<Dish>
	 */
	public List<Dish> findDishByRestaurantId(String restaurantId) {
		return dishDao.findDishByRestaurantId(restaurantId);
	}
	/**
	 * 添加菜肴信息
	 * @param dish
	 * @return
	 */
	public Integer addDish(Dish dish){
		return dishDao.addDish(dish);
	}
	
	/**
	 * 删除菜肴信息
	 * @param dish
	 * @return
	 */
	public Integer deleteDish(Dish dish){
		
		return dishDao.deleteDish(dish);
	}
	/**
	 * 更新菜肴信息
	 * @param dish
	 * @return
	 */
	public Integer updateDish(Dish dish){
		Dish _dish=dishDao.findDishByDishId(dish.getDishId());
		_dish.setDishImage(dish.getDishImage());
		
		
		sss
		return dishDao.updateDish(_dish);
	}
}
