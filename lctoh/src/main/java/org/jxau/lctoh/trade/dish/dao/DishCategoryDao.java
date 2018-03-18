package org.jxau.lctoh.trade.dish.dao;

import java.util.Date;
import java.util.List;






import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.jxau.lctoh.trade.dish.mapper.DishCategoryMapper;
import org.jxau.lctoh.trade.dish.mapper.DishMapper;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
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
		if(dishCategory==null)return dishCategory;
		return loadDiahCategoryDishList(dishCategory);
	}
	
	/**
	 * 加载单个菜肴分类下的菜肴信息
	 * @param dishCategory
	 * @return DishCategory
	 */
	private DishCategory loadDiahCategoryDishList(DishCategory dishCategory) {
		dishCategory.setDishList(dishMapper.findDishByDishCategoryId(dishCategory.getDishCategoryId()));
		return dishCategory;
	}
	/**
	 * 加载多个菜肴分类下的菜肴信息
	 * @param dishCategoryList
	 * @return  List<DishCategory> 
	 */
	private List<DishCategory> loadDiahCategoryListDishList(List<DishCategory>  dishCategoryList) {
		for(int i=0;i<dishCategoryList.size();i++){
			dishCategoryList.set(i, loadDiahCategoryDishList(dishCategoryList.get(i)));
		}
		return dishCategoryList;
	}
	
	/**
	 * 根据店家识别码查询分类信息
	 * @param dishCategoryId
	 * @return DishCategory
	 */
	public List<DishCategory> findDishCategoryByRestaurantId(String restaurantId){
		List<DishCategory> dishCategoryList=dishCategoryMapper.findDishCategoryByRestaurantId(restaurantId);
		if(dishCategoryList==null)return dishCategoryList;
		return loadDiahCategoryListDishList(dishCategoryList);
	}
	
	
	/**
	 * 添加分类信息
	 * @param dishCategory
	 * @return Integer
	 */
	public Integer addDishCategory(DishCategory dishCategory){
		dishCategory.setDishCategoryUpdateTime(new Date());
		return dishCategoryMapper.addDishCategory(dishCategory);
	}
	/**
	 * 更新分类信息
	 * @param dishCategory
	 * @return Integer
	 */
	public Integer updateDishCategory(DishCategory dishCategory) {
		dishCategory.setDishCategoryUpdateTime(new Date());
		return dishCategoryMapper.updateDishCategory(dishCategory);
	}
	
	/**
	 * 删除菜肴分类
	 * @param dishCategory
	 * @return
	 */
	public Integer deleteDishCategory(DishCategory dishCategory) {
		dishCategory.setDishCategoryUpdateTime(new Date());
		return dishCategoryMapper.deleteDishCategory(dishCategory);
	}
}
