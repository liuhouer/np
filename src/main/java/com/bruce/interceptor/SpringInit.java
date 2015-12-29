package com.bruce.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringInit implements ServletContextListener {
    
	 
    private static WebApplicationContext springContext;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}
     
	    public SpringInit() {
	        super();
	    }
	     
	    public static ApplicationContext getApplicationContext() {
	        return springContext;
	    }
	 
     
}