package org.jxau.lctoh.user.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.admin.domain.Admin;

/**
 * 管理员类代理接口
 * */
public interface AdminMapper {

	/**
	 * 根据用户识别码查询管理员信息
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 */
	public Admin findAdminByUserId(@Param("userId")String userId);
	
	
	/**
	 * 根据管理员识别码查询管理员信息
	 * @param adminId  管理员识别码
	 * @return Admin 管理员
	 */
	public Admin findAdminByAdminId(@Param("adminId")String adminId);
	
	
	
}
