package org.jxau.lctoh.tool.config;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component("ClassInfoMSG")
public class ClassInfoMSG {
	/**默认昵称*/
	public final static String customerNickname="用户";
	/**默认头像*/
	public final static String customerPortrait="/image.jpg";
	/**默认账户余额*/
	public final static BigDecimal customerBalance=BigDecimal.valueOf(100);
	/**客户默认状态*/
	public final static Integer customerStateId=20002;
	
}
