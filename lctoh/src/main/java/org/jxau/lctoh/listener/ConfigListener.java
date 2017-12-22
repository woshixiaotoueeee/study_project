package org.jxau.lctoh.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jxau.lctoh.tool.config.Config;
import org.jxau.lctoh.user.rider.domain.Rider;

public class ConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Map<String,Rider> riderMap=new LinkedHashMap<String,Rider>();
		servletContextEvent.getServletContext().setAttribute(Config.riderContext, riderMap);;
	}

}
