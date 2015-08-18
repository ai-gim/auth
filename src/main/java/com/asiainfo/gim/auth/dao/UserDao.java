/**
 * @File: UserDao.java 
 * @Package  com.asiainfo.gim.auth.dao
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午11:01:22 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.dao;

import java.util.List;

import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.domain.query.UserQueryCondition;

/**
 * @author luyang
 *
 */
public interface UserDao
{
	public List<User> listUsers(UserQueryCondition queryCondition);
	
	public User findUserByID(int id);
	
	public User findUserByAccount(String account);
	
	public void insertUser(User user);
	
	public void insertUserAndRoleRelation(User user);
	
	public void insertUserAndGroupRelation(User user);
	
	public void updateUser(User user);
	
	public void updateUserPassword(User user);
	
	public void updateUserWithRole(User user);
	
	public void updateUserWithGroup(User user);
	
	public void deleteUser(int id);
	
	public void deleteUserAndRole(int id);
	
	public void deleteUserAndGroup(int id);
}
