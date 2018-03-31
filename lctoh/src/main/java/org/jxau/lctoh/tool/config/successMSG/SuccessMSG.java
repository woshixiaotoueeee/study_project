package org.jxau.lctoh.tool.config.successMSG;

import java.io.File;

import org.springframework.stereotype.Component;

/**
 * 操作成功响应信息常量池
 * @author qdt_PC
 */
@Component("SuccessMSG")
public class SuccessMSG {
	/**成功响应编码*/
	public final static Integer successType=1;

	/**验证码时效*/
	public final static Integer timeExpire=1000*60*5;
	
	/**定位成功信息*/
	public final static String locationSuccessMSG="定位成功";
	
	/**管理员登陆成功后所需跳转的页面地址*/
	public final static String adminSuccessUrl=File.separator.concat("admin").concat(File.separator).concat("index.html");
	/**客户登陆成功后所需跳转的页面地址*/
	public final static String customerSuccessUrl=File.separator.concat("customer").concat(File.separator).concat("index.html");
	/**骑手登陆成功后所需跳转的页面地址*/
	public final static String riderSuccessUrl=File.separator.concat("rider").concat(File.separator).concat("index.html");
	/**店家登陆成功后所需跳转的页面地址*/
	public final static String restaurantSuccessUrl=File.separator.concat("restaurant").concat(File.separator).concat("index.html");
	/**邮件发送成功*/
	public final static String sendEmailSuccess="验证通过";
	
	/**操作成功*/
	public final static String successMSG="操作成功";
	/**删除成功*/
	public final static String deleteSuccessMSG="删除成功";
	/**添加成功*/
	public final static String addSuccessMSG="添加成功";
	/**修改成功*/
	public final static String updateSuccessMSG="修改成功";
	
}
