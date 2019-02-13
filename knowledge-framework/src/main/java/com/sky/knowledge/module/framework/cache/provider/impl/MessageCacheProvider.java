package com.sky.knowledge.module.framework.cache.provider.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.shared.util.Properties;


@Component("messageCacheProvider")
public class MessageCacheProvider implements IBatchCacheProvider<String, Properties> {
    
    private Log logger = LogFactory.getLog(MessageCacheProvider.class);
    private Date startTime = new Date();
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.provider.IBatchCacheProvider#get()
     * get
     * @throws 
     * @since:0.6
     */
    @Override
    public Map<String, Properties> get() {
        Map<String, Properties> map = new HashMap<String, Properties>();
        final String prefix =  "com/sky/";
        try {
        	//加载指定位置的properties文件
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:" + prefix
                    + "**/server/META-INF/messages/message*.properties");
            for (Resource resource : resources) {
                String path = resource.getURL().getPath();
                String classpath = path.substring(path.lastIndexOf(prefix));
                if (logger.isInfoEnabled()) {
                    logger.info("[Framework] add message bundle: " + classpath);
                }
                //取得文件名
                final String moduleName = classpath.replaceAll("/server/META-INF/.*$", "").replaceAll("^.*/", "");
                final String localeName = classpath.replaceAll(".*\\/message([_a-zA-Z]*)\\.properties$", "$1");
                Properties properties = new Properties();
                InputStream in = resource.getInputStream();
                try {
                    properties.load(in);
                } finally {
                    in.close();
                }
                map.put(moduleName + localeName, properties);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return map;
    }

    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheProvider#getLastModifyTime()
     * getLastModifyTime
     * @return
     * @since:
     */
	@Override
	public Date getLastModifyTime() {
		return startTime;
	}
    
}
