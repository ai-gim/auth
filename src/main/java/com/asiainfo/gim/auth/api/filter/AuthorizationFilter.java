/**   
 * @Title: AuthorizationFilter.java 
 * @Package com.asiainfo.gim.auth.api.filter 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年7月8日 下午4:34:22 
 * @version V1.0   
 */
package com.asiainfo.gim.auth.api.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.auth.api.TokenUtils;
import com.asiainfo.gim.auth.domain.Token;

/**
 * @author zhangli
 *
 */
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter
{
	@Override
	public void filter(ContainerRequestContext context) throws IOException
	{
		if (!StringUtils.equals(context.getUriInfo().getPath(), "token"))
		{
			if (context.getHeaders().containsKey("X-AUTH-TOKEN"))
			{
				String tokenId = context.getHeaders().get("X-AUTH-TOKEN").get(0);
				Token token = TokenUtils.getToken(tokenId);
				if (token == null)
				{
					context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				}
			}
			else
			{
				context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		}
	}
}
