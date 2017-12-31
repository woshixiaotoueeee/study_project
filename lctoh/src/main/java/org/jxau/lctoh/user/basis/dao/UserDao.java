package org.jxau.lctoh.user.basis.dao;


import java.util.Date;

import org.jxau.lctoh.tool.base.BaseDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.mapper.UserMapper;
import org.springframework.stereotype.Repository;
@Repository("UserDao")
public class UserDao extends BaseDao{
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public void puttMapper() {
		userMapper=getMapper(UserMapper.class);
	}
	

	/**
	 * 根据用户识别码查询用户信息
	 * @param userId  用户识别码
	 * @return User 用户
	 */
	public User findUserByUserId(String userId){
		return userMapper.findUserByUserId(userId);
	}
	
	
	/**
	 * 根据用户账号查询用户信息
	 * @param userAccount  用户账号
	 * @return User 用户
	 */
	public User findUserByUserAccount(String userAccount){
		return userMapper.findUserByUserAccount(userAccount);
	}
	
	
	/**
	 * 根据用户邮箱查询用户信息
	 * @param userEmail  用户邮箱
	 * @return User 用户
	 */
	public User findUserByUserEmail(String userEmail){
		return userMapper.findUserByUserEmail(userEmail);
	}
	
	/**
	 * 根据用户激活码查询用户信息
	 * @param userCode  用户激活码
	 * @return User 用户
	 */
	public User findUserByUserCode(String userCode){
		return userMapper.findUserByUserCode(userCode);
	}
	
	
	
	/**
	 * 添加用户
	 * @param user 需要添加的用户信息
	 * @return Integer 添加数量
	 */
	public Integer addUser(User user){
		user.setUserUpdateTime(new Date());
		return userMapper.addUser(user);
	}
	
	
	/**
	 * 更新用户信息
	 * @param user 需要更新的用户信息
	 * @return Integer 添加数量
	 */
	public Integer updateUser(User user){
		user.setUserUpdateTime(new Date());
		return userMapper.updateUser(user);
	}
	
	
	
}
