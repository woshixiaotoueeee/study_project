package org.jxau.lctoh.user.rider.dao;


import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.user.admin.domain.Admin;
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
	 * 根据用户识别码查询管理员信息
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 */
	public Admin findRiderByUserId(String userId){
		return riderMapper.findRiderByUserId(userId);
	}
	
	
	/**
	 * 根据管理员识别码查询管理员信息
	 * @param adminId  管理员识别码
	 * @return Admin 管理员
	 */
	public Admin findRiderByRiderId(String riderId){
		return riderMapper.findRiderByRiderId(riderId);
	}
	
	
	
	
}
