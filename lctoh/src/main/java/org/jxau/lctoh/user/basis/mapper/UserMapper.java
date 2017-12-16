package org.jxau.lctoh.user.basis.mapper;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.basis.domain.User;


/**
 * 用户类代理接口
 * */
public interface UserMapper {

	/**
	 * 根据用户识别码查询用户信息
	 * @param userId  用户识别码
	 * @return User 用户
	 */
	public User findUserByUserId(@Param("userId")String userId);
	
	
	/**
	 * 根据用户账号查询用户信息
	 * @param userAccount  用户账号
	 * @return User 用户
	 */
	public User findUserByUserAccount(@Param("userAccount")String userAccount);
	
	
	/**
	 * 根据用户邮箱查询用户信息
	 * @param userEmail  用户邮箱
	 * @return User 用户
	 */
	public User findUserByUserEmail(@Param("userEmail")String userEmail);
	
	/**
	 * 根据用户激活码查询用户信息
	 * @param userCode  用户激活码
	 * @return User 用户
	 */
	public User findUserByUserCode(@Param("userCode")String userCode);
	
	
	
	/**
	 * 添加用户
	 * @param user 需要添加的用户信息
	 * @return Integer 添加数量
	 */
	public Integer addUser(User user);
	
	
	/**
	 * 更新用户信息
	 * @param user 需要更新的用户信息
	 * @return Integer 添加数量
	 */
	public Integer updateUser(User user);
	
	
}
