package com.sky.knowledge.module.framework.server.deploy.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.sky.knowledge.module.framework.server.util.IOUtils;
/**
 * 将各个模块 META-INF下的文件 转放到 WEB-INF目录下，方便struts返回jsp页面
 * @description
 * @create xq
 * @date 2014-3-28
 */
public class ModuleManager {
	private static final Log logger = LogFactory.getLog(ModuleManager.class);

	private static PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	private static final String PACKAGE_PREFIX = "com/sky/knowledge/";

	private static final String PAGES_FROM = "/server/META-INF/pages/";
	
	private static final String PAGES_TO = "/WEB-INF/pages/";

	private static final String PAGES_SUFFIX = "";
	
	private static final String SCRIPTS_FROM = "/server/META-INF/scripts/";
	
	private static final String SCRIPTS_TO = "/scripts/";

	private static final String SCRIPTS_SUFFIX = "";
	
	private static final String STYLES_FROM = "/server/META-INF/styles/";
	
	private static final String STYLES_TO = "/styles/";

	private static final String STYLES_SUFFIX = "";
	
	private static final String IMAGES_FROM = "/server/META-INF/images/";
	
	private static final String IMAGES_TO = "/images/";

	private static final String IMAGES_SUFFIX = "";
	
	public static void export(ServletContext servletContext) {
		//输出pages
		export(servletContext, PAGES_FROM, PAGES_TO, PAGES_SUFFIX);
		//输出scripts
		export(servletContext, SCRIPTS_FROM, SCRIPTS_TO, SCRIPTS_SUFFIX);
		//输出styles
		export(servletContext, STYLES_FROM, STYLES_TO, STYLES_SUFFIX);
		//输出images
		export(servletContext, IMAGES_FROM, IMAGES_TO, IMAGES_SUFFIX);
	}
	
	/**
	 * 输出
	 * export
	 * @param servletContext
	 * @param from
	 * @param to
	 * @param suffix
	 * @return void
	 * @since:0.6
	 */
	private static void export(ServletContext servletContext, String from, String to, String suffix) {
		try {
			if (logger.isInfoEnabled()) {
				logger.info("[Framework] servlet root dir: " + servletContext.getRealPath("/"));
			}
			Resource[] resources = resolver.getResources("classpath*:" + PACKAGE_PREFIX + "**" + from + "**" + suffix);
			if (resources != null && resources.length > 0) {
				for (Resource resource : resources) {
					try {
						String path = resource.getURL().getPath();
						
						int j = path.lastIndexOf(from);
					
						String pathHeader = path.substring(0, j);
						
						int i = pathHeader.lastIndexOf("/");
						String module = path.substring(i + 1, j);
						String page = path.substring(j + from.length());
						String dist = to + module + "/" + page;
						
						File file = new File(servletContext.getRealPath(dist));
						if (!file.getName().matches("^.*[.].*$")) {
							File dir = new File(file.getPath() + "\\" + File.separator);
							//
							if (!dir.exists()) {
								file.mkdirs();
							}
						}
						File dir = file.getParentFile();
						if (! dir.exists()) {
							dir.mkdirs();
							if (logger.isInfoEnabled()) {
								logger.info("[Framework] create dir: " + dir);
							}
						}
						if (logger.isInfoEnabled()) {
							logger.info("[Framework] release resource: " + dist);
						}
						OutputStream out = new FileOutputStream(file);
						InputStream in = resource.getInputStream();
						IOUtils.copy(in, out, true);	
						
						//璁剧疆閲婃斁鏂囦欢鐨勬渶鍚庝慨鏀规椂闂�
						file.setLastModified(resource.lastModified());
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
}
