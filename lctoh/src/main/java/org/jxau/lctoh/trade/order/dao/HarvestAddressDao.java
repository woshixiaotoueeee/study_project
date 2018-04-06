package org.jxau.lctoh.trade.order.dao;

import java.util.Date;

import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.order.domain.HarvestAddress;
import org.jxau.lctoh.trade.order.mapper.HarvestAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("HarvestAddressDao")
public class HarvestAddressDao extends BaseDao {
	@Autowired
	private HarvestAddressMapper harvestAddressMapper;
	
	public HarvestAddressMapper getHarvestAddressMapper() {
		return harvestAddressMapper;
	}
	public void setHarvestAddressMapper(HarvestAddressMapper harvestAddressMapper) {
		this.harvestAddressMapper = harvestAddressMapper;
	}
	@Override
	public void puttMapper() {
		harvestAddressMapper=this.getMapper(HarvestAddressMapper.class);
	}
	
	/**
	 * 根据配送地址识别码查询地址信息
	 * @param harvestAddressId
	 * @return
	 */
	public HarvestAddress findHarvestAddressByhaid(String harvestAddressId) {
		return harvestAddressMapper.findHarvestAddressByhaid(harvestAddressId);
	}
	
	
	/**
	 * 根据配送地址识别码查询地址信息
	 * @param harvestAddressId
	 * @return
	 */
	public Integer addHarvestAddress(HarvestAddress harvestAddress) {
		harvestAddress.setHarvestAddressUpdateTime(new Date());
		return harvestAddressMapper.addHarvestAddress(harvestAddress);
	}
}
