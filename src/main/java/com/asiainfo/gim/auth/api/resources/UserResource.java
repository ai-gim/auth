/**
 * @File: UserResource.java 
 * @Package  com.asiainfo.gim.auth.api.resources
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 下午2:06:14 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.auth.api.validator.UserValidator;
import com.asiainfo.gim.auth.domain.Group;
import com.asiainfo.gim.auth.domain.Role;
import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.service.GroupService;
import com.asiainfo.gim.auth.service.RoleService;
import com.asiainfo.gim.auth.service.UserService;
import com.asiainfo.gim.common.rest.exception.ResourceNotFoundException;
import com.asiainfo.gim.common.rest.exception.ValidationException;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author luyang
 *
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
	private UserService userService;
	
	private RoleService roleService;
	
	private GroupService groupService;
	
	@PathParam("id")
	private int id;

	public UserResource()
	{
		userService = SpringContext.getBean(UserService.class);
		roleService = SpringContext.getBean(RoleService.class);
		groupService = SpringContext.getBean(GroupService.class);
	}
	
	@GET
	@Path("{id}")
	public User getUser()
	{
		User user = userService.findUserByID(id);
		if (user == null)
		{
			throw new ResourceNotFoundException();
		}
		return user;
	}
	
	@PUT
	@Path("{id}")
	public User updateUser(@UserValidator User user)
	{
		if (user == null)
		{
			throw new ValidationException();
		}

		User userInDb = userService.findUserByID(id);
		if (userInDb == null)
		{
			throw new ResourceNotFoundException();
		}

		if (!StringUtils.isEmpty(user.getAccount()) && !StringUtils.equals(user.getAccount(), userInDb.getAccount()))
		{
			throw new ValidationException("Account can't change");
		}

		Role role = roleService.findRoleByID(user.getRole().getId());
		if (role == null)
		{
			throw new ValidationException("Role not exist");
		}
		
		Group group = groupService.findGroupByID(user.getGroup().getId());
		if (group == null)
		{
			throw new ValidationException("Group not exist");
		}
		
		user.setId(id);
		
		return userService.updateUser(user);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(@UserValidator User user)
	{
		if (user == null)
		{
			throw new ValidationException();
		}

		User userWithSameAccount = userService.findUserByAccount(user.getAccount());
		if (userWithSameAccount != null)
		{
			throw new ValidationException("Account conflict");
		}
		
		Role role = roleService.findRoleByID(user.getRole().getId());
		if (role == null)
		{
			throw new ValidationException("Role not exist");
		}
		
		if (user.getGroup() != null && user.getGroup().getId() != null)
		{
			Group group = groupService.findGroupByID(user.getGroup().getId());
			if (group == null)
			{
				throw new ValidationException("Group not exist");
			}
		}
		
		return userService.addUser(user);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUser()
	{
		userService.deleteUser(id);
	}
}
