package org.jxau.lctoh.tool.config;

import org.springframework.stereotype.Component;

/**通用常量池*/
@Component("ErrorMSG")
public class ErrorMSG {

	/**账号错误信息*/
	public final static String accountAndPasswordIsNullError="账号和密码不能为空";
	/**账号错误信息*/
	public final static String accountIsNullError="账号不能为空";
	/**密码错误信息*/
	public final static String passwordIsNullError="密码不能为空";
	
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
}
