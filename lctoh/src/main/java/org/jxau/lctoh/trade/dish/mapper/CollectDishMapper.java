package org.jxau.lctoh.trade.dish.mapper;

import org.jxau.lctoh.trade.dish.domain.CollectDish;

/**
 * @author qdt_PC
 */
public interface CollectDishMapper {
	/**
	 * 添加收藏的菜肴
	 * @param collectDish
	 * @return
	 */
	public Integer addCollectDish(CollectDish collectDish);
	/**
	 * 删除收藏的菜肴
	 * @param collectDish
	 * @return
	 */
	public Integer deleteCollectDish(CollectDish collectDish);
}
