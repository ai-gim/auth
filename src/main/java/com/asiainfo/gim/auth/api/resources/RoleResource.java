/**
 * @File: RoleResource.java 
 * @Package  com.asiainfo.gim.auth.api.resources
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午9:38:56 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.api.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.auth.api.validator.RoleValidator;
import com.asiainfo.gim.auth.domain.Role;
import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.domain.query.UserQueryCondition;
import com.asiainfo.gim.auth.service.RoleService;
import com.asiainfo.gim.auth.service.UserService;
import com.asiainfo.gim.common.rest.exception.ResourceNotFoundException;
import com.asiainfo.gim.common.rest.exception.ValidationException;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author luyang
 *
 */
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource
{
	private RoleService roleService;

	private UserService userService;

	@PathParam("id")
	private int id;

	public RoleResource()
	{
		roleService = SpringContext.getBean(RoleService.class);
		userService = SpringContext.getBean(UserService.class);
	}

	@GET
	@Path("{id}")
	public Role getRole()
	{
		Role role = roleService.findRoleByID(id);
		if (role == null)
		{
			throw new ResourceNotFoundException();
		}
		return role;
	}

	@PUT
	@Path("{id}")
	public Role updateRole(@RoleValidator Role role)
	{
		if (role == null)
		{
			throw new ValidationException();
		}

		Role roleInDb = roleService.findRoleByID(id);
		if (roleInDb == null)
		{
			throw new ResourceNotFoundException();
		}

		if (!StringUtils.equals(role.getName(), roleInDb.getName()))
		{
			Role roleWithSameName = roleService.findRoleByName(role.getName());
			if (roleWithSameName != null)
			{
				throw new ValidationException("Name conflict");
			}
		}

		role.setId(id);

		return roleService.updateRole(role);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Role addRole(@RoleValidator Role role)
	{
		if (role == null)
		{
			throw new ValidationException();
		}

		Role roleWithSameName = roleService.findRoleByName(role.getName());
		if (roleWithSameName != null)
		{
			throw new ValidationException("Name conflict");
		}

		return roleService.addRole(role);
	}

	@DELETE
	@Path("{id}")
	public void deleteRole()
	{
		UserQueryCondition queryCondition = new UserQueryCondition();
		queryCondition.setRoleId(id);
		List<User> users = userService.listUsers(queryCondition);
		if (users != null && users.size() > 0)
		{
			throw new ValidationException("This role contains user");
		}

		roleService.deleteRole(id);
	}
}
