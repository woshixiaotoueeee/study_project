package org.jxau.lctoh.user.rider.dao;


import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.jxau.lctoh.user.rider.mapper.RiderMapper;
import org.springframework.stereotype.Repository;
@Repository("RiderDao")
public class RiderDao extends BaseDao {
	
	private RiderMapper riderMapper;
	public RiderMapper getRiderMapper() {
		return riderMapper;
	}
	public void setRiderMapper(RiderMapper riderMapper) {
		this.riderMapper = riderMapper;
	}
	@Override
	public void puttMapper() {
		riderMapper=this.getMapper(RiderMapper.class);
	}
	
	/**
	 * 根据用户识别码查询骑手信息
	 * @param userId  用户识别码
	 * @return Rider 骑手
	 */
	public Rider findRiderByUserId(String userId){
		return riderMapper.findRiderByUserId(userId);
	}
	
	
	/**
	 * 根据骑手识别码查询骑手信息
	 * @param riderId  骑手识别码
	 * @return Rider 骑手
	 */
	public Rider findRiderByRiderId(String riderId){
		return riderMapper.findRiderByRiderId(riderId);
	}
	
	
	
	
}
