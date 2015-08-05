/**   
 * @Title: AuthApplication.java 
 * @Package com.asiainfo.gim.auth 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年7月8日 上午9:53:11 
 * @version V1.0   
 */
package com.asiainfo.gim.auth;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.asiainfo.gim.auth.api.filter.AuthorizationFilter;
import com.asiainfo.gim.common.rest.exception.DefaultExceptionMapper;
import com.asiainfo.gim.common.rest.filter.LogFilter;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * @author zhangli
 *
 */
public class AuthApplication extends ResourceConfig
{
	public AuthApplication()
	{
		packages("com.asiainfo.gim.auth.api.resources");

		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

		register(JacksonJsonProvider.class);
		register(DefaultExceptionMapper.class);
		register(LogFilter.class);

		register(AuthorizationFilter.class);
	}
}
