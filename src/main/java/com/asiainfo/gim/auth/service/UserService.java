/**
 * @File: UserService.java 
 * @Package  com.asiainfo.gim.auth.service
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午11:08:53 
 * @version V1.0
 * 
 */
package com.asiainfo.gim.auth.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.asiainfo.gim.auth.dao.UserDao;
import com.asiainfo.gim.auth.domain.Group;
import com.asiainfo.gim.auth.domain.User;
import com.asiainfo.gim.auth.domain.query.UserQueryCondition;

/**
 * @author luyang
 *
 */
@Service
public class UserService
{
	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
	public List<User> listUsers()
	{
		return listUsers(new UserQueryCondition());
	}
	
	public List<User> listUsers(UserQueryCondition queryCondition)
	{
		return userDao.listUsers(queryCondition);
	}

	public User findUserByID(int id)
	{
		return userDao.findUserByID(id);
	}

	public User findUserByAccount(String account)
	{
		return userDao.findUserByAccount(account);
	}

	public User addUser(User user)
	{
		user.setTime(new Date());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userHasGroup(user);
		
		userDao.insertUser(user);
		userDao.insertUserAndRoleRelation(user);
		userDao.insertUserAndGroupRelation(user);
		return userDao.findUserByAccount(user.getAccount());
	}

	public User updateUser(User user)
	{
		userHasGroup(user);
		
		if (StringUtils.isEmpty(user.getPassword()))
		{
			userDao.updateUser(user);
			userDao.updateUserWithRole(user);
			userDao.updateUserWithGroup(user);
		}
		else
		{
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			userDao.updateUserPassword(user);
		}
		
		return userDao.findUserByID(user.getId());
	}
	
	public void deleteUser(int id)
	{
		userDao.deleteUser(id);
		userDao.deleteUserAndRole(id);
		userDao.deleteUserAndGroup(id);
	}
	
	private void userHasGroup(User user)
	{
		if (user.getGroup() == null || user.getGroup().getId() == null)
		{
			Group group = new Group();
			group.setId(1);
			user.setGroup(group);
		}
	}
}
