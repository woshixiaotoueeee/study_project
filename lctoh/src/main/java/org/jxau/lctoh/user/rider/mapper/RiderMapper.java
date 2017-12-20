package org.jxau.lctoh.user.rider.mapper;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.admin.domain.Admin;

/**
 * 管理员类代理接口
 * */
public interface RiderMapper {

	/**
	 * 根据用户识别码查询管理员信息
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 */
	public Admin findRiderByUserId(@Param("userId")String userId);
	
	
	/**
	 * 根据管理员识别码查询管理员信息
	 * @param adminId  管理员识别码
	 * @return Admin 管理员
	 */
	public Admin findRiderByRiderId(@Param("riderId")String riderId);
	
	
	
}
