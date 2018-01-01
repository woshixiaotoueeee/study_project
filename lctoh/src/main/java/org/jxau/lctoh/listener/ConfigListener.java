package org.jxau.lctoh.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.user.rider.domain.Rider;

public class ConfigListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {}
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		//系统启动时创建一个储存骑手信息的Map
		Map<String,Rider> riderMap=new LinkedHashMap<String,Rider>();
		servletContextEvent.getServletContext().setAttribute(ConversationMSG.riderContext, riderMap);;
	}
}