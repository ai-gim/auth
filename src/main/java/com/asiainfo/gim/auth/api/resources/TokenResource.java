/**   
 * @Title: TokenResource.java 
 * @Package com.asiainfo.gim.auth.api.resources 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年7月27日 下午4:47:40 
 * @version V1.0   
 */
package com.asiainfo.gim.auth.api.resources;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.internal.util.Base64;

import com.asiainfo.gim.auth.api.TokenUtils;
import com.asiainfo.gim.auth.domain.Token;
import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.service.UserService;
import com.asiainfo.gim.common.rest.exception.UnAuthorizedException;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author zhangli
 *
 */
@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource
{
	@Context
	private ContainerRequestContext context;
	
	private UserService userService;
	
	public TokenResource()
	{
		userService = SpringContext.getBean(UserService.class);
	}

	@GET
	public Token getToken()
	{
		if (context.getHeaders().containsKey("Authorization"))
		{
			return basicAuthorize();
		}
		else if (context.getHeaders().containsKey("X-AUTH-TOKEN"))
		{
			return tokenAuthorize();
		}
		else
		{
			throw new UnAuthorizedException();
		}
	}

	private Token basicAuthorize()
	{
		String userAndPasswdBase64 = StringUtils.substring(context.getHeaderString("Authorization"), 6);
		String[] userAndPasswdArr = StringUtils.split(Base64.decodeAsString(userAndPasswdBase64), ":");

		if (userAndPasswdArr.length != 2)
		{
			throw new UnAuthorizedException();
		}

		String username = userAndPasswdArr[0];
		String password = userAndPasswdArr[1];
		
		// validate user account and password
		User user = userService.findUserByAccount(username);
		if(user == null)
		{
			throw new UnAuthorizedException();
		}
		if(!StringUtils.equals(user.getPassword(), DigestUtils.md5Hex(password)))
		{
			throw new UnAuthorizedException();
		}

		// set token id and expired
		Token token = new Token();
		token.setId(UUID.randomUUID().toString());
		
		// set user info
		user.setPassword(null);
		token.setUser(user);

		// cache token
		TokenUtils.cacheToken(token);

		return token;
	}

	private Token tokenAuthorize()
	{
		String tokenId = context.getHeaderString("X-AUTH-TOKEN");

		Token token = TokenUtils.getToken(tokenId);
		if (token == null)
		{
			throw new UnAuthorizedException();
		}
		return token;
	}
}
