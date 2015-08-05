/**   
 * @Title: Main.java 
 * @Package com.asiainfo.gim.auth 
 * @Description: AIM-AUTHORIZATION MAIN
 * @author zhangli
 * @date 2015年7月8日 上午9:49:40 
 * @version V1.0   
 */
package com.asiainfo.gim.auth;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author zhangli
 *
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		// 初始化Spring
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-base.xml", "spring-db.xml");

		// 初始化日志
		DOMConfigurator.configure(Main.class.getClassLoader().getResource("log4j.xml"));

		// 启动 HTTP Server
		String host = SpringContext.getProperty("service_auth_host");
		int port = NumberUtils.toInt(SpringContext.getProperty("service_auth_port"), 9001);

		URI baseUri = UriBuilder.fromUri("http://" + host).port(port).build();
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, new AuthApplication());
		server.start();
	}

}
