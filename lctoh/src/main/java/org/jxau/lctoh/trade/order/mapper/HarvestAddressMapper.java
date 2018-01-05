package org.jxau.lctoh.trade.order.mapper;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.trade.order.domain.HarvestAddress;

/**
 * @author qdt_PC
 *
 */
public interface HarvestAddressMapper {
	
	/**
	 * 根据配送地址识别码查询地址信息
	 * @param harvestAddressId
	 * @return
	 */
	public HarvestAddress findHarvestAddressByhaid(@Param("harvestAddressId")String harvestAddressId);
	
	/**
	 * 更改配送地址数据
	 * @param harvestAddress
	 * @return
	 */
	public Integer updateHarvestAddress(HarvestAddress harvestAddress);
	
	/**
	 * 插入一个配送地址
	 * @param harvestAddress
	 * @return
	 */
	public Integer addHarvestAddress(HarvestAddress harvestAddress);
	
}
