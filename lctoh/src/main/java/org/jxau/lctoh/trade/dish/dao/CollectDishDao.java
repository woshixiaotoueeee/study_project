package org.jxau.lctoh.trade.dish.dao;

import java.util.Date;
import java.util.List;





import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.dish.domain.CollectDish;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.trade.dish.mapper.CollectDishMapper;
import org.jxau.lctoh.trade.dish.mapper.DishMapper;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("CollectDishDao")
public class CollectDishDao extends BaseDao {
	private CollectDishMapper collectDishMapper;
	
	public CollectDishMapper getCollectDishMapper() {
		return collectDishMapper;
	}
	public void setCollectDishMapper(CollectDishMapper collectDishMapper) {
		this.collectDishMapper = collectDishMapper;
	}
	@Override
	public void puttMapper() {
		collectDishMapper=this.getMapper(CollectDishMapper.class);
	}
	
	/**
	 * 添加收藏的菜肴
	 * @param collectDish
	 * @return
	 */
	public Integer addCollectDish(CollectDish collectDish){
		collectDish.setCollectDishUpdateTime(new Date());
		return collectDishMapper.addCollectDish(collectDish);
	}
	/**
	 * 删除收藏的菜肴
	 * @param collectDish
	 * @return
	 */
	public Integer deleteCollectDish(CollectDish collectDish){
		collectDish.setCollectDishUpdateTime(new Date());
		return collectDishMapper.deleteCollectDish(collectDish);
	}
}
