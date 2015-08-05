/**
 * @File: RoleService.java 
 * @Package  com.asiainfo.gim.auth.service
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午9:33:01 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.asiainfo.gim.auth.dao.RoleDao;
import com.asiainfo.gim.auth.domain.Role;

/**
 * @author luyang
 *
 */
@Service
public class RoleService
{
	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao)
	{
		this.roleDao = roleDao;
	}
	
	public List<Role> listRoles()
	{
		return roleDao.listRoles();
	}

	public Role findRoleByID(int id)
	{
		return roleDao.findRoleByID(id);
	}

	public Role findRoleByName(String name)
	{
		return roleDao.findRoleByName(name);
	}

	public Role addRole(Role role)
	{
		roleDao.insertRole(role);
		return roleDao.findRoleByName(role.getName());
	}

	public Role updateRole(Role role)
	{
		roleDao.updateRole(role);
		return roleDao.findRoleByName(role.getName());
	}

	public void deleteRole(int id)
	{
		roleDao.deleteRole(id);
	}
}
