package com.jeesite.modules.webSocket.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.util.WebAppRootListener;


@Configuration
public class WebsocketConfiguration implements ServletContextInitializer{

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(WebAppRootListener.class);
        servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize","52428800");//5M
        servletContext.setInitParameter("org.apache.tomcat.websocket.binaryBufferSize","10240000");
	}
}
