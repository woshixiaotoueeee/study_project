package org.jxau.lctoh.user.admin.service;

import org.jxau.lctoh.user.admin.dao.AdminDao;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("AdminService")
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 管理员登陆
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 */
	public Admin login (User user) throws UserException{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException("账号不存在");
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException("密码错误");
		Admin admin=adminDao.findAdminByUserId(_user.getUserId());
		if(admin==null)throw new UserException("抱歉，你的账号没有该权限");
		
		/**后期可能需要修改*/
		if(admin.getAdminState().getStateId()!=10002)
			throw new UserException("抱歉，你的账号可能未激活或者被锁定无法登陆");
		return admin;
	}
	
}
