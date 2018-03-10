package org.jxau.lctoh.tool.config.defaultInformation;

import java.io.File;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;
/**
 * 部分实体类默认信息
 * @author qdt_PC
 */
@Component("DefaultInformation")
public class DefaultInformation {
	/**默认昵称*/
	public final static String customerNickname="用户";
	/**默认头像*/
	public final static String customerPortrait=(File.separator.concat("image.jpg"));
	/**默认账户余额*/
	public final static BigDecimal customerBalance=BigDecimal.valueOf(100);
	/**客户默认状态*/
	public final static Integer customerStateId=20002;//激活
	
	/**地址默认状态编码*/
	public final static Integer addressdeFaultStateId=60002;
	/**地址非默认状态编码*/
	public final static Integer addressNotFaultStateId=60003;
}
