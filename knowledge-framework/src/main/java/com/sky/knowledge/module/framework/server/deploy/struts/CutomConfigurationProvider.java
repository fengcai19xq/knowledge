package com.sky.knowledge.module.framework.server.deploy.struts;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.providers.XmlConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.util.location.LocatableProperties;
/**
 * 自动解析配置文件，在服务启动时加载 各个模块的 struts.xml
 * @description
 * @create xq
 * @date 2014-3-31
 */
public class CutomConfigurationProvider extends XmlConfigurationProvider{

	private static final Log logger = LogFactory
			.getLog(CutomConfigurationProvider.class);
	
	private static final String FILE_PATTERN = "classpath*:com/sky/knowledge/**/server/META-INF/struts.xml" ;

	public CutomConfigurationProvider(){
	}
	public void register(ContainerBuilder containerBuilder, LocatableProperties props) throws ConfigurationException {
		super.register(containerBuilder, props);
	}
    /**
     * 重写父类的 getConfigurationUrls方法
     * @description
     * @create xq
     * @date 2014-3-31
     */
	protected Iterator<URL> getConfigurationUrls(String fileName) throws IOException {
		List<URL> urls = new ArrayList<URL>() ;
		Resource[] resources = getAllResourcesUrl() ;
		for(Resource resource : resources ){
			urls.add( resource.getURL()) ;
		}
		return urls.iterator() ;
	}
	/**
	 * 获取资源文件
	 * @description
	 * @create xq
	 * @date 2014-3-31
	 */
	private Resource[] getAllResourcesUrl() {
		ResourcePatternResolver resoler = new PathMatchingResourcePatternResolver() ;
		try {
			return resoler.getResources(FILE_PATTERN) ;
			} catch (IOException e) {
			e.printStackTrace();
			}
			return new Resource[0];
	}
}
