package com.sky.knowledge.module.framework.server.web.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.cache.init.impl.MessageCache;
import com.sky.knowledge.module.framework.server.context.RequestContext;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.shared.util.Properties;


/**
 * 
 * 
 ******************************************* 
 * <b style="font-family:微软雅黑"><small>Description:国际化资源接口实现类</small></b> </br>
 * <b style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ******************************************** 
 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-3-27 梁飞 新增 </div>
 ******************************************** 
 */
@Component
public class MessageBundle implements IMessageBundle {

	/**
	 * 
	 * @see com.deppon.foss.framework.server.web.message.IMessageBundle#getMessage(java.util.Locale,
	 *      java.lang.String, java.lang.Object[]) getMessage
	 * @param locale
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getMessage(Locale locale, String key, Object... args) {
		ICache<String, Properties> cache = CacheManager.getInstance().getCache(
				MessageCache.UUID);
		String moduleName = RequestContext.getCurrentContext().getModuleName();
		Properties properties = cache.get(moduleName + "_" + locale);
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get(moduleName + "_" + locale.getLanguage());
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get(moduleName);
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		// 当前模块下没有国际化信息则从框架扩展模块获取
		properties = cache.get("frameworkimpl" + "_" + locale);
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get("frameworkimpl" + "_" + locale.getLanguage());
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}
		
		properties = cache.get("frameworkimpl");
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}
		
		// 没有国际化信息返回key
		return key;
		
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.server.web.message.IMessageBundle#getMessage(java.lang.String,
	 *      java.lang.Object[]) getMessage
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getMessage(String key, Object... args) {
		return getMessage(UserContext.getUserLocale(), key, args);
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.server.web.message.IMessageBundle#getDynamicMessage(java.lang.String,
	 *      java.lang.Object[]) getDynamicMessage
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getDynamicMessage(String key, Object... args) {
		return getDynamicMessage(UserContext.getUserLocale(), key, args);
	}

	/**
	 * 
	 * @see com.deppon.foss.framework.server.web.message.IMessageBundle#getDynamicMessage(java.util.Locale,
	 *      java.lang.String, java.lang.Object[]) getDynamicMessage
	 * @param locale
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getDynamicMessage(Locale locale, String key, Object... args) {
		@SuppressWarnings("unchecked")
		ICache<String, Map<String, String>> cache = CacheManager.getInstance()
				.getCache(AbstractDynamicMessageCache.UUID);
		Map<String, String> map = cache.get(locale.toString());
		if (map == null) {
			return key;
		}
		String message = map.get(key);
		if (message == null)
			return key;
		return (args == null || args.length == 0) ? message : MessageFormat
				.format(message, args);
	}

}