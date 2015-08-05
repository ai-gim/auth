/**   
 * @Title: UserQueryCondition.java 
 * @Package com.asiainfo.gim.auth.domain.query 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年7月30日 上午10:26:11 
 * @version V1.0   
 */
package com.asiainfo.gim.auth.domain.query;

/**
 * @author zhangli
 *
 */
public class UserQueryCondition extends QueryCondition
{
	private Integer roleId;
	private Integer groupId;

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}
}
