/**
 * @File: UserValidator.java 
 * @Package  com.asiainfo.gim.auth.api.validator
 * @Description: 
 * @author luyang
 * @date 2015年7月29日 下午2:10:06 
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

import com.asiainfo.gim.auth.domain.User;

/**
 * @author luyang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.Valicator.class)
public @interface UserValidator
{
	String message() default "Role bean validate fail!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	public class Valicator implements ConstraintValidator<UserValidator, User>
	{
		@Context
		private ContainerRequestContext context;
		
		@Override
		public void initialize(UserValidator uv)
		{
			
		}

		@Override
		public boolean isValid(User user, ConstraintValidatorContext cvc)
		{
			if (StringUtils.equals(context.getMethod(), "POST"))
			{
				if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getAccount()))
				{
					return false;
				}
				return validUser(user);
			}
			else if (StringUtils.equals(context.getMethod(), "PUT"))
			{
				return validUser(user);
			}
			return true;
		}

		private boolean validUser(User user)
		{
			if (user.getState() == null)
			{
				return false;
			}
			
			if (user.getRole() == null || user.getRole().getId() == null)
			{
				return false;
			}
			
			return true;
		}
	}
}
