package com.sky.knowledge.module.framework.server.deploy.struts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.opensymphony.xwork2.FileManagerFactory;
import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.ConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.location.LocatableProperties;
import com.sky.knowledge.module.framework.server.web.filter.FrameworkFilter;

public class ModuleConfigProvider implements ConfigurationProvider{

	private static final Log logger = LogFactory
			.getLog(ModuleConfigProvider.class);

	private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	private List<ConfigurationProvider> providers = new ArrayList<ConfigurationProvider>();

	/**
	 * 加载各个模块的struts.xml文件
	 */
	public ModuleConfigProvider() {
		try {
			ServletContext servletContext = FrameworkFilter.getServletContext();
			Resource[] resources = resolver
					.getResources("classpath*:com/sky/knowledge/**/server/META-INF/struts.xml");
			for (Resource resource : resources) {
				String path = resource.getURL().getPath();
				String classpath = path.substring(path
						.lastIndexOf("com/sky/knowledge/"));
				if (logger.isInfoEnabled()) {
					logger.info("[Framework] add action config: " + classpath);
				}
				providers.add(new StrutsXmlConfigurationProvider(classpath,
						true, servletContext));
			}
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	 public void init(Configuration config)
			    throws ConfigurationException
			  {
			    for (ConfigurationProvider provider : this.providers)
			      provider.init(config);
			  }

			  public void destroy()
			  {
			    for (ConfigurationProvider provider : this.providers)
			      provider.destroy();
			  }

			  public void loadPackages()
			    throws ConfigurationException
			  {
			    for (ConfigurationProvider provider : this.providers)
			      provider.loadPackages();
			  }

			  public boolean needsReload()
			  {
			    for (ConfigurationProvider provider : this.providers) {
			      if (provider.needsReload()) {
			        return true;
			      }
			    }
			    return false;
			  }

			  public void register(ContainerBuilder builder, LocatableProperties properties)
			    throws ConfigurationException
			  {
			    for (ConfigurationProvider provider : this.providers)
			      provider.register(builder, properties);
			  }

			  @Inject
			  public void setObjectFactory(ObjectFactory objectFactory)
			  {
			    for (ConfigurationProvider provider : this.providers) {
			      StrutsXmlConfigurationProvider p = (StrutsXmlConfigurationProvider)provider;
			      p.setObjectFactory(objectFactory);
			    }
			  }

			  @Inject
			  public void setFileManagerFactory(FileManagerFactory fileManagerFactory) {
			    for (ConfigurationProvider provider : this.providers) {
			      StrutsXmlConfigurationProvider p = (StrutsXmlConfigurationProvider)provider;
			      p.setFileManagerFactory(fileManagerFactory);
			    }
			  }
			}