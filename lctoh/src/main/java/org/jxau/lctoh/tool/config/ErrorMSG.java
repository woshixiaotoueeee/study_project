package org.jxau.lctoh.tool.config;

import org.springframework.stereotype.Component;

/**通用常量池*/
@Component("ErrorMSG")
public class ErrorMSG {

	/**账号密码为空错误信息*/
	public final static String accountAndPasswordIsNullError="账号和密码不能为空";
	/**账号为空错误信息*/
	public final static String accountIsNullError="账号不能为空";
	/**密码为空错误信息*/
	public final static String passwordIsNullError="密码不能为空";
	
	/**账号不存在错误信息*/
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
	/**验证码错误*/
	public final static String codeError="验证码有误";
	/**验证码失效*/
	public final static String timeExpireError="验证码已失效";
	
	/**邮件发送失败*/
	public final static String emailSendError="邮件发送失败";
	
	/**成功响应编码*/
	public final static String success="1";
	/**失败响应编码*/
	public final static String fail="0";
	
	/**验证码时效*/
	public final static Integer timeExpire=1000*60*5;
	
	
	
	
	
}
