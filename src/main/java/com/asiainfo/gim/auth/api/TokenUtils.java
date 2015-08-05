/**   
 * @Title: TokenUtils.java 
 * @Package com.asiainfo.gim.auth.api 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhangli
 * @date 2015年7月28日 上午9:52:06 
 * @version V1.0   
 */
package com.asiainfo.gim.auth.api;

import java.util.Calendar;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;

import com.asiainfo.gim.auth.domain.Token;
import com.asiainfo.gim.common.spring.SpringContext;

/**
 * @author zhangli
 *
 */
public class TokenUtils
{
	public static void cacheToken(Token token)
	{
		CacheManager cacheManager = (CacheManager) SpringContext.getBean("cacheManager");
		Cache cache = cacheManager.getCache("TOKEN_CACHE");
		
		if(token.getExpired() == null)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 30);
			token.setExpired(calendar.getTime());
		}
		
		cache.put(token.getId(), token);
	}

	public static Token getToken(String tokenId)
	{
		CacheManager cacheManager = (CacheManager) SpringContext.getBean("cacheManager");
		Cache cache = cacheManager.getCache("TOKEN_CACHE");

		ValueWrapper vm = cache.get(tokenId);
		if (vm == null)
		{
			return null;
		}

		Token token = (Token) vm.get();
		if (token.getExpired().getTime() < System.currentTimeMillis())
		{
			cache.evict(tokenId);
			return null;
		}
		
		return token;
	}
}
