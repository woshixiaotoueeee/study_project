package org.jxau.lctoh.tool.config;

import org.springframework.stereotype.Component;

/**
 * 操作成功响应信息常量池
 * @author qdt_PC
 */
@Component("SuccessMSG")
public class SuccessMSG {
	/**成功响应编码*/
	public final static Integer success=1;

	/**验证码时效*/
	public final static Integer timeExpire=1000*60*5;
	
	/**定位成功信息*/
	public final static String locationSuccessMSG="定位成功";
	
	/**管理员登陆成功后所需跳转的页面地址*/
	public final static String adminSuccessUrl="/admin/index.html";
	/**客户登陆成功后所需跳转的页面地址*/
	public final static String customerSuccessUrl="/custmer/index.html";
	/**骑手登陆成功后所需跳转的页面地址*/
	public final static String riderSuccessUrl="/rider/index.html";
	/**店家登陆成功后所需跳转的页面地址*/
	public final static String restaurantSuccessUrl="/restaurant/index.html";
	/**邮件发送成功*/
	public final static String sendEmailSuccess="验证通过";
	
}
