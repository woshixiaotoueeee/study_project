package org.jxau.lctoh.user.restaurant.dao;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.dish.mapper.DishCategoryMapper;
import org.jxau.lctoh.user.restaurant.domain.CollectRestaurant;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.mapper.CollectRestaurantMapper;
import org.jxau.lctoh.user.restaurant.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("CollectRestaurantDao")
public class CollectRestaurantDao extends BaseDao {
	@Autowired
	private CollectRestaurantMapper collectRestaurantMapper;
	
	public CollectRestaurantMapper getCollectRestaurantMapper() {
		return collectRestaurantMapper;
	}
	public void setCollectRestaurantMapper(
			CollectRestaurantMapper collectRestaurantMapper) {
		this.collectRestaurantMapper = collectRestaurantMapper;
	}
	@Override
	public void puttMapper() {
		collectRestaurantMapper=this.getMapper(CollectRestaurantMapper.class);
	}
	
	/**
	 * 添加收藏的店家
	 * @param collectRestaurant
	 * @return
	 */
	public Integer addCollectRestaurant(CollectRestaurant collectRestaurant){
		collectRestaurant.setCollectRestaurantUpdateTime(new Date());
		return collectRestaurantMapper.addCollectRestaurant(collectRestaurant);
	}
	/**
	 * 删除收藏的店家
	 * @param collectRestaurant
	 * @return
	 */
	public Integer deleteCollectRestaurant(CollectRestaurant collectRestaurant){
		collectRestaurant.setCollectRestaurantUpdateTime(new Date());
		return collectRestaurantMapper.deleteCollectRestaurant(collectRestaurant);
	}
	
	

}
