/**   
* @Title: GroupValidator.java 
* @Package com.asiainfo.gim.auth.api.validator 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangli
* @date 2015年7月8日 下午2:53:30 
* @version V1.0   
*/
package com.asiainfo.gim.auth.api.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.auth.domain.Group;

/**
 * @author zhangli
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupValidator.Valicator.class)
public @interface GroupValidator
{
	String message() default "Group bean validate fail!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	public class Valicator implements ConstraintValidator<GroupValidator, Group>
	{
		@Context
		private ContainerRequestContext context;
		
		@Override
		public void initialize(GroupValidator gv)
		{
			
		}

		@Override
		public boolean isValid(Group group, ConstraintValidatorContext cvc)
		{
			if (StringUtils.equals(context.getMethod(), "POST"))
			{
				return validGroup(group);
			}
			else if (StringUtils.equals(context.getMethod(), "PUT"))
			{
				return validGroup(group);
			}
			return true;
		}

		private boolean validGroup(Group group)
		{
			if (StringUtils.isEmpty(group.getName()))
			{
				return false;
			}
			return true;
		}
	}
}
