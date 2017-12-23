package org.jxau.lctoh.tool.config;

import org.springframework.stereotype.Component;
/**通用常量池*/
@Component("Config")
public class Config {
	/**设置的编码*/
	public final static String characterEncoding="utf-8";
	/**默认的编码*/
	public final static String charEncoding="iso8859-1";
	/**controller响应编码格式*/
	public final static String produces="text/html;charset=utf-8";
	
	
	
	
	/**账号错误信息*/
	public final static String accountError="账号不存在";
	/**密码错误信息*/
	public final static String passwordError="密码错误";
	/**权限错误信息*/
	public final static String powerError="抱歉，你没有权限";
	/**登陆状态错误信息*/
	public final static String loginStateError="抱歉，你的账号可能未激活或者被锁定无法登陆";
	/**未知错误信息*/
	public final static String notKnowError="未知错误，请联系管理员";
	/**身份不明信息*/
	public final static String notKnowUserError="身份不明";
	
	
	
	
	/**成功响应编码*/
	public final static String success="1";
	/**失败响应编码*/
	public final static String fail="0";
	
	
	
	/**定位信息存于Session对应的键*/
	public final static String locationSession="location";
	/**管理员信息存于Session对应的键*/
	public final static String adminSession="admin";
	/**店家信息存于Session对应的键*/
	public final static String restaurantSession="restaurant";
	/**客户信息存于Session对应的键*/
	public final static String customerSession="customer";
	/**骑手信息存于Session对应的键*/
	public final static String riderSession="rider";
	/**骑手信息存于Context对应的键*/
	public final static String riderContext="riderMap";
	
}
