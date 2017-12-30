package org.jxau.lctoh.tool.config;

import org.springframework.stereotype.Component;

/**通用常量池*/
@Component("EmailConfing")
public class EmailConfing {
	/**服务器邮箱账号*/
	public final static String myEmailAccount = "1285739190@qq.com";
	/**服务器邮箱密码*/
	public final static String myEmailPassword = "zgusakqxxbcbgddh";
	/**邮箱服务器*/
	public final static String myEmailSMTPHost = "smtp.qq.com";
	
	/**发送协议*/
	public final static String mailTransportProtocol = "smtp";
	/**是否验证*/
	public final static String mailSmtpAuth = "true";
	/**邮箱发送短口*/
	public final static String mailSmtPort = "587";
	/**调式模式*/
	public final static Boolean debug=true;
	/**sendTile*/
	public final static String sendTile="联创点餐平台";
	
	
	/**popTile*/
	public final static String popTile="尊敬的用户";
	
	/**sendMSGCodeTile*/
	public final static String sendMSGCodeTile="验证码";
	/**验证码邮件内容*/
	public final static String  sendMSGCodeInfo ="你的验证码是：";
	
	
	
	
	
	
	

}
