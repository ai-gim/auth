/**   
 * @Title: GroupService.java 
 * @Package com.asiainfo.gim.auth.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年7月9日 上午11:46:50 
 * @version V1.0   
 */
package com.asiainfo.gim.auth.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.asiainfo.gim.auth.dao.GroupDao;
import com.asiainfo.gim.auth.domain.Group;

/**
 * @author zhangli
 *
 */
@Service
public class GroupService
{
	private GroupDao groupDao;

	@Resource
	public void setGroupDao(GroupDao groupDao)
	{
		this.groupDao = groupDao;
	}

	public List<Group> listGroups()
	{
		return groupDao.listGroups();
	}

	public Group findGroupByID(int id)
	{
		return groupDao.findGroupByID(id);
	}

	public Group findGroupByName(String name)
	{
		return groupDao.findGroupByName(name);
	}

	public Group addGroup(Group group)
	{
		groupDao.insertGroup(group);
		return groupDao.findGroupByName(group.getName());
	}

	public Group updateGroup(Group group)
	{
		groupDao.updateGroup(group);
		return groupDao.findGroupByName(group.getName());
	}

	public void deleteGroup(int id)
	{
		groupDao.deleteGroup(id);
	}
}
