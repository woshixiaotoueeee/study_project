package org.jxau.lctoh.trade.dish.dao;

import java.util.List;



import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.jxau.lctoh.trade.dish.mapper.DishCategoryMapper;
import org.jxau.lctoh.trade.dish.mapper.DishMapper;
import org.springframework.stereotype.Repository;

@Repository("DishCategoryDao")
public class DishCategoryDao  extends BaseDao {

	
	private DishCategoryMapper dishCategoryMapper;
	private DishMapper dishMapper;
	
	public DishCategoryMapper getDishCategoryMapper() {
		return dishCategoryMapper;
	}
	public void setDishCategoryMapper(DishCategoryMapper dishCategoryMapper) {
		this.dishCategoryMapper = dishCategoryMapper;
	}
	public DishMapper getDishMapper() {
		return dishMapper;
	}
	public void setDishMapper(DishMapper dishMapper) {
		this.dishMapper = dishMapper;
	}
	@Override
	public void puttMapper() {
		dishCategoryMapper=this.getMapper(DishCategoryMapper.class);
		dishMapper=this.getMapper(DishMapper.class);
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
			dishCategory.setDishList(dishMapper.findDishByDishCategoryId(dishCategoryId));
		}
		return dishCategory;
	}
	
	/**
	 * 根据店家识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public List<DishCategory> findDishCategoryByRestaurantId(String restaurantId){
		List<DishCategory> dishCategoryList=dishCategoryMapper.findDishCategoryByRestaurantId(restaurantId);;
		if(dishCategoryList!=null){
			DishCategory dishCategory;
			for(int i=0;i<dishCategoryList.size();i++){
				dishCategory=dishCategoryList.get(i);
				dishCategory.setDishList(dishMapper.findDishByDishCategoryId(dishCategory.getDishCategoryId()));
			}
		}
		return dishCategoryMapper.findDishCategoryByRestaurantId(restaurantId);
	}
}
