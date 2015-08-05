/**
 * @File: RoleDao.java 
 * @Package  com.asiainfo.gim.auth.dao
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午9:24:47 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.dao;

import java.util.List;

import com.asiainfo.gim.auth.domain.Role;

/**
 * @author luyang
 *
 */
public interface RoleDao
{
	public List<Role> listRoles();
	
	public Role findRoleByID(int id);
	
	public Role findRoleByName(String name);
	
	public void insertRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(int id);
}
