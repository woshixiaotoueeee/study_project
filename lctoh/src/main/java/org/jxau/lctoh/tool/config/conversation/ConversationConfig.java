package org.jxau.lctoh.tool.config.conversation;

import org.springframework.stereotype.Component;
/**
 * 用于会话信息 键 的通用常量池
 * @author qdt_PC
 */
@Component("ConversationConfig")
public class ConversationConfig {

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
	/**验证信息存于Session对应的键*/
	public final static String userSession="user";
	/**购物车信息存于Session对应的键*/
	public final static String cartSession="cart";
	
}
