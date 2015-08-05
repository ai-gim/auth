/**
 * @File: UserListResource.java 
 * @Package  com.asiainfo.gim.auth.api.resources
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午11:11:32 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.api.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.domain.query.UserQueryCondition;
import com.asiainfo.gim.auth.service.UserService;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author luyang
 *
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserListResource
{
	private UserService userService;

	@QueryParam("roleId")
	private Integer roleId;
	
	@QueryParam("groupId")
	private Integer groupId;
	
	public UserListResource()
	{
		userService = SpringContext.getBean(UserService.class);
	}

	@GET
	public List<User> getUser()
	{
		UserQueryCondition userQueryCondition = new UserQueryCondition();
		userQueryCondition.setGroupId(groupId);
		userQueryCondition.setRoleId(roleId);
		
		List<User> users = userService.listUsers(userQueryCondition);
		return users;
	}
}
