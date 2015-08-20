/**   
 * @Title: TokenClearTask.java 
 * @Package com.asiainfo.gim.auth.task 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年8月20日 上午10:18:45 
 * @version V1.0   
 */
package com.asiainfo.gim.auth.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asiainfo.gim.auth.api.TokenUtils;
import com.asiainfo.gim.auth.domain.Token;

/**
 * @author zhangli
 *
 */
@Component
public class TokenClearTask
{
	@Scheduled(cron = "0 0/30 * * * ?")
	public void execute()
	{
		for (Object o : TokenUtils.getAllTokens())
		{
			Token token = (Token) o;
			if (token.getExpired().getTime() < System.currentTimeMillis())
			{
				TokenUtils.evictToken(token.getId());
			}
		}
	}
}
