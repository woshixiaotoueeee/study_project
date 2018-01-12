package org.jxau.lctoh.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;




import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.user.rider.domain.Rider;

/**
 * 在系统启动的时候给系统创建一个储存骑手位置的Map
 * @author qdt_PC
 */
public class ConfigListener implements ServletContextListener {
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		//系统启动时创建一个储存骑手信息的Map
		Map<String,Rider> riderMap=new LinkedHashMap<String,Rider>();
		servletContextEvent.getServletContext().setAttribute(ConversationConfig.riderContext, riderMap);;
	}
}