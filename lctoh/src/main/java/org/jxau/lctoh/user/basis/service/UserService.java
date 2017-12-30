package org.jxau.lctoh.user.basis.service;


import java.util.Date;

import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	/**登陆*/
	public void login(User user){
		System.out.println(userDao.findUserByUserAccount("100001"));
		System.out.println(userDao.findUserByUserCode("qwertyuioppoiuytrewqqwertyuioppo"));
		System.out.println(userDao.findUserByUserEmail("1234@qq.com"));
		System.out.println(userDao.findUserByUserId("123456"));
		User u=userDao.findUserByUserId("123456");
		System.out.println(userDao.updateUser(u));
		u.setUserId("1111111111111111111111111111");
		System.out.println(userDao.addUser(u));
	}
	
	
	
	
}
