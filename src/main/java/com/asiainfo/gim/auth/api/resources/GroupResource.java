/**   
 * @Title: GroupResource.java 
 * @Package com.asiainfo.gim.auth.api.resources 
 * @Description: Group Resource
 * @author zhangli
 * @date 2015年7月8日 上午8:58:57 
 * @version V1.0   
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

import com.asiainfo.gim.auth.api.validator.GroupValidator;
import com.asiainfo.gim.auth.domain.Group;
import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.domain.query.UserQueryCondition;
import com.asiainfo.gim.auth.service.GroupService;
import com.asiainfo.gim.auth.service.UserService;
import com.asiainfo.gim.common.rest.exception.ResourceNotFoundException;
import com.asiainfo.gim.common.rest.exception.ValidationException;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author zhangli
 *
 */
@Path("/group")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource
{
	private GroupService groupService;

	private UserService userService;
	
	@PathParam("id")
	private int id;

	public GroupResource()
	{
		groupService = SpringContext.getBean(GroupService.class);
		userService = SpringContext.getBean(UserService.class);
	}

	@GET
	@Path("{id}")
	public Group getGroup()
	{
		Group group = groupService.findGroupByID(id);
		if (group == null)
		{
			throw new ResourceNotFoundException();
		}
		return group;
	}
	
	@PUT
	@Path("{id}")
	public Group updateGroup(@GroupValidator Group group)
	{
		if (group == null)
		{
			throw new ValidationException();
		}

		Group groupInDb = groupService.findGroupByID(id);
		if (groupInDb == null)
		{
			throw new ResourceNotFoundException();
		}

		if (!StringUtils.equals(group.getName(), groupInDb.getName()))
		{
			Group groupWithSameName = groupService.findGroupByName(group.getName());
			if (groupWithSameName != null)
			{
				throw new ValidationException("Name conflict");
			}
		}

		group.setId(id);
		
		return groupService.updateGroup(group);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Group addGroup(@GroupValidator Group group)
	{
		if (group == null)
		{
			throw new ValidationException();
		}

		Group groupWithSameName = groupService.findGroupByName(group.getName());
		if (groupWithSameName != null)
		{
			throw new ValidationException("Name conflict");
		}
		
		return groupService.addGroup(group);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteGroup()
	{
		UserQueryCondition userQueryCondition = new UserQueryCondition();
		userQueryCondition.setGroupId(id);
		List<User> users = userService.listUsers(userQueryCondition);
		if (users != null && users.size() > 0)
		{
			throw new ValidationException("This group contains user");
		}
		
		groupService.deleteGroup(id);
	}
}
