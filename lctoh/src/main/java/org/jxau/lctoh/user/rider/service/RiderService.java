package org.jxau.lctoh.user.rider.service;

import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.rider.dao.RiderDao;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("RiderService")
public class RiderService {
	@Autowired
	private RiderDao riderDao;
	@Autowired
	private UserDao userDao;
	
	
	/**
	 * 管理员登陆
	 * @param userId  用户识别码
	 * @return Admin 管理员
	 * @throws UserException
	 * @throws Exception
	 */
	public Rider login (User user) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException("账号不存在");
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException("密码错误");
		Rider rider=riderDao.findRiderByUserId(_user.getUserId());
		if(rider==null)throw new UserException("抱歉，你的账号没有该权限");
		
		/**后期可能需要修改*/
		if(rider.getRiderState().getStateId()!=10002)
			throw new UserException("抱歉，你的账号可能未激活或者被锁定无法登陆");
		return rider;
	}
}
