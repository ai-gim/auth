/**   
* @Title: GroupDao.java 
* @Package com.asiainfo.gim.auth.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangli
* @date 2015年7月9日 上午11:06:40 
* @version V1.0   
*/
package com.asiainfo.gim.auth.dao;

import java.util.List;

import com.asiainfo.gim.auth.domain.Group;

/**
 * @author zhangli
 *
 */
public interface GroupDao
{
	public List<Group> listGroups();
	
	public Group findGroupByID(int id);
	
	public Group findGroupByName(String name);
	
	public void insertGroup(Group group);
	
	public void updateGroup(Group group);
	
	public void deleteGroup(int id);
}
