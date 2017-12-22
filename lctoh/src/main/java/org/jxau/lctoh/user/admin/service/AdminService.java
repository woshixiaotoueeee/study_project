package org.jxau.lctoh.user.admin.service;

import org.jxau.lctoh.tool.config.Config;
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
	 * @throws UserException
	 * @throws Exception
	 */
	public Admin login (User user) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(Config.accountError);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(Config.passwordError);
		Admin admin=adminDao.findAdminByUserId(_user.getUserId());
		if(admin==null)throw new UserException(Config.powerError);
		
		/**后期可能需要修改*/
		if(admin.getAdminState().getStateId()!=10002)
			throw new UserException(Config.loginStateError);
		return admin;
	}
	
}
