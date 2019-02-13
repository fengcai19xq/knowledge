package com.sky.knowledge.module.login.server.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.knowledge.module.framework.shared.string.StringUtil;



public class AttachmentRootPath
{
  public static Log logger = LogFactory.getLog(AttachmentRootPath.class);
  private static String linuxServerpath;

  public static String getAttachRootPath()
  {
    if (StringUtil.isBlank(linuxServerpath))
    {
      initConfig();
    }

    String osname = System.getProperty("os.name");

    String rootPath = "";

    if (osname.contains("Windows"))
    {
      rootPath = AttachmentRootPath.class.getClassLoader().getResource("")
        .getPath();

      rootPath = rootPath.substring(0, rootPath.lastIndexOf("WEB-INF"));
    }
    else {
      rootPath = linuxServerpath;
    }

    logger.info("存储附件目录：" + rootPath);
    return rootPath;
  }

  public static void initConfig()
  {
    AttachmentConfig config = (AttachmentConfig)
      SpringContextUtil.getBean("attachmentConfig");

    linuxServerpath = config.getLinuxServerPath();
  }
}