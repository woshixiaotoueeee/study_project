package org.jxau.lctoh.user.restaurant.mapper;

import org.jxau.lctoh.user.restaurant.domain.CollectRestaurant;



/**
 * @author qdt_PC
 */
public interface CollectRestaurantMapper {
	
	/**
	 * 添加收藏的店家
	 * @param collectRestaurant
	 * @return
	 */
	public Integer addCollectRestaurant(CollectRestaurant collectRestaurant);
	/**
	 * 删除收藏的店家
	 * @param collectRestaurant
	 * @return
	 */
	public Integer deleteCollectRestaurant(CollectRestaurant collectRestaurant);
}
