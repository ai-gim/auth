/**
 * @File: RoleValidator.java 
 * @Package  com.asiainfo.gim.auth.api.validator
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 上午9:44:39 
 * @version V1.0
 * 
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

import com.asiainfo.gim.auth.domain.Role;

/**
 * @author luyang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidator.Valicator.class)
public @interface RoleValidator
{
	String message() default "Role bean validate fail!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	public class Valicator implements ConstraintValidator<RoleValidator, Role>
	{
		@Context
		private ContainerRequestContext context;
		
		@Override
		public void initialize(RoleValidator rv)
		{
			
		}

		@Override
		public boolean isValid(Role role, ConstraintValidatorContext cvc)
		{
			if (StringUtils.equals(context.getMethod(), "POST"))
			{
				return validRole(role);
			}
			else if (StringUtils.equals(context.getMethod(), "PUT"))
			{
				return validRole(role);
			}
			return true;
		}

		private boolean validRole(Role role)
		{
			if (StringUtils.isEmpty(role.getName()))
			{
				return false;
			}
			return true;
		}
		
	}
}
