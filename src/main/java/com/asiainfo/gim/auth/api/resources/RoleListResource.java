/**
 * @File: RoleListResource.java 
 * @Package  com.asiainfo.gim.auth.api.resources
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午9:39:24 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.api.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.asiainfo.gim.auth.domain.Role;
import com.asiainfo.gim.auth.service.RoleService;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author luyang
 *
 */
@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleListResource
{
	private RoleService roleService;
	
	public RoleListResource()
	{
		roleService = SpringContext.getBean(RoleService.class);
	}
	
	@GET
	public List<Role> getRole()
	{
		List<Role> roles = roleService.listRoles();
		return roles;
	}
}
