package org.jxau.lctoh.user.admin.dao;

import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.admin.mapper.AdminMapper;
import org.springframework.stereotype.Repository;
@Repository("AdminDao")
public class AdminDao extends BaseDao {
	
	private AdminMapper adminMapper;
	
	
	public AdminMapper getAdminMapper() {
		return adminMapper;
	}
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	@Override
	public void puttMapper() {
		adminMapper=this.getMapper(AdminMapper.class);

	}
	/**
	 * 根据用户识别码查询管理员信息
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 */
	public Admin findAdminByUserId(String userId) {
		return adminMapper.findAdminByUserId(userId);
	}
	/**
	 * 根据管理员识别码查询管理员信息
	 * @param adminId  管理员识别码
	 * @return Admin 管理员
	 */
	public Admin findAdminByAdminId(String adminId) {
		return adminMapper.findAdminByAdminId(adminId);
	}
	
	
	
}
