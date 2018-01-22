package org.jxau.lctoh.tool.config.error;

import org.springframework.stereotype.Component;

/**
 * 验证错误信息存放处
 * @author qdt_PC
 */
@Component("ErrorMSG")
public class ErrorMSG {
	/**失败响应编码*/
	public final static Integer failType=0;
	
	//传值
	/**账号密码为空错误信息*/
	public final static String accountAndPasswordIsNull="账号和密码不能为空";
	/**账号为空错误信息*/
	public final static String accountIsNull="账号为空";
	/**密码为空错误信息*/
	public final static String passwordIsNull="密码为空";
	/**联系方式错误*/
	public final static String phoneIsNull="联系方式为空";
	/**性别为空*/
	public final static String sexIsNull="为选择性别";
	/**验证码为空*/
	public final static String codeIsNullError="验证码为空";
	/**参数为空*/
	public final static String parameterIsNull="参数为空";
	/**省份城市信息为空*/
	public final static String provinceAndCityIsNull="省份城市信息为空";
	/**地址联系人或联系方式为空*/
	public final static String addressNameOrPhoneIsNull="地址联系人或联系方式空";
	
	//操作
	/**权限错误信息*/
	public final static String noPower="抱歉，你没有权限";
	/**登陆状态错误信息*/
	public final static String loginStateError="抱歉，你的账号可能未激活或者被锁定无法登陆";
	/**未知错误信息*/
	public final static String notKnow="未知错误，请联系管理员，或刷新页面重试";
	/**身份不明信息*/
	public final static String notKnowUser="身份不明";
	/**验证码错误*/
	public final static String codeError="验证码有误";
	/**验证码失效*/
	public final static String codeTimeExpire="验证码已失效";
	
	
	
	/**密码错误信息*/
	public final static String passwordError="密码错误";
	/**两次密码不同*/
	public final static String passwordNotSameError="两次密码不同";
	/**账号不存在错误信息*/
	public final static String accountInexistence="账号不存在";
	
	
	/**邮件发送失败*/
	public final static String emailSendFail="邮件发送失败";
	/**邮箱格式错误*/
	public final static String emailFormatError="邮箱格式错误";
	/**邮箱为空*/
	public final static String emailIsNull="邮箱为空";
	/**邮箱存在*/
	public final static String emailExistence="邮箱已被注册";
	
	
	/**计算购物车价格失败*/
	public final static String putCartTotalFail="计算购物车总价失败";
	/**添加失败*/
	public final static String addFail="添加失败,请稍后重试";
	/**删除失败*/
	public final static String deleteFail="删除失败,请稍后重试";
	/**查询失败*/
	public final static String selectFail="查询失败,请稍后重试";
	/**查询失败*/
	public final static String updateFail="更改失败,请稍后重试";
	/**操作失败*/
	public final static String operationFail="操作失败,请稍后重试";
	/**查询为空*/
	public final static String gerInfoIsNull="查询为空";
	/**获取定位信息失败*/
	public final static String getLocationFail="未定位或定位信息失效导致获取定位信息失败，请重新定位";
	
	/**地址有误*/
	public final static String addressError="地址有误导致操作失败";
	
	/**登陆状态过期*/
	public final static String loginTimerOut="您可能未登陆或者登陆状态过期，请重新登陆";
	
	/**购物车失效*/
	public final static String  cartTimerOut="购物车失效，请重新登录";
	
	/**菜肴不存在*/
	public final static String noDish="菜肴不存在";
	
	
	/**余额不足*/
	public final static String insufficienFunds="余额不足";
}
