/**   
* @Title: GroupListResource.java 
* @Package com.asiainfo.gim.auth.api.resources 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangli
* @date 2015年7月27日 下午3:15:24 
* @version V1.0   
*/
package com.asiainfo.gim.auth.api.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.asiainfo.gim.auth.domain.Group;
import com.asiainfo.gim.auth.service.GroupService;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author zhangli
 *
 */
@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupListResource
{
	private GroupService groupService;
	
	public GroupListResource()
	{
		groupService = SpringContext.getBean(GroupService.class);
	}
	
	@GET
	public List<Group> getGroup()
	{
		List<Group> groups = groupService.listGroups();
		return groups;
	}
}
