package com.sky.knowledge.module.login.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.login.server.service.IHomePageService;
import com.sky.knowledge.module.login.server.util.AttachmentRootPath;
import com.sky.knowledge.module.login.shared.domain.HomePageEntity;


public class HomePageAction extends AbstractAction
{
  private static final long serialVersionUID = 8741964272983856457L;
  private Logger log = Logger.getLogger(HomePageAction.class.getName());
  private static final String HELP_PAGE = "helpFile/";

  @Resource
  private IHomePageService homePageService;
  private HomePageEntity homePageEntity;
  private List<HomePageEntity> list;
  private InputStream inputStream;
  private String fileName;

  public String query()
  {
    try
    {
      this.log.info("[query]开始查询");
      this.list = this.homePageService.queryAll(this.homePageEntity);
      this.log.info("[query]查询结束");
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.log.info("[query]返回");
    return "success";
  }

  public String downloadFile() {
    try {
      this.log.info("[downloadFile]开始下载");
      if ((this.homePageEntity == null) || 
        (this.homePageEntity.getHomePageID() == null)) {
        this.log.error("[downloadFile]前端传给后端的参数为NULL");
        return null;
      }
      this.log.info("[downloadFile]开始根据ID查询数据");
      HomePageEntity entity = this.homePageService.query(this.homePageEntity);
      if (entity == null) {
        this.log.error("[downloadFile]根据ID，从数据库中读取数据失败");
        return null;
      }

      String filePath = AttachmentRootPath.getAttachRootPath() + "helpFile/" + entity.getReadFileName();
      this.log.info("[downloadFile]开始获取路径:" + filePath);
      File file = new File(filePath);
      if ((file != null) && (file.isFile())) {
        this.fileName = encodeFileName(entity.getFileName());
        this.log.info("[downloadFile]对文件名进行编码，由[" + entity.getFileName() + "]变为[" + this.fileName + "]");
        this.log.info("[downloadFile]读取文件成功，并生成流");
        this.inputStream = new FileInputStream(file);  
        this.log.info("[downloadFile]下载结束，并返回");
        return "download";
      }
      this.log.error("[downloadFile]读取文件失败");
      return "fileNotExist";
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
      return "fileNotExist";
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return "fileNotExist";
    } catch (Exception e) {
      e.printStackTrace();
      return "fileNotExist";
    }
  }

  public static String encodeFileName(String fileName) throws UnsupportedEncodingException
  {
    try {
    	
      String agent = ServletActionContext.getRequest().getHeader(
        "USER-AGENT");
      if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
        fileName = URLEncoder.encode(fileName, "UTF8");  
        }
      if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
        String fileNameTemp = fileName.replace(" ", "");
        fileName = new String(fileNameTemp.getBytes("GB2312"), 
          "ISO-8859-1");
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      try {
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
      } catch (UnsupportedEncodingException e1) {
        e1.printStackTrace();
        return null;
      }
      return null;
    }
    return fileName;
  }

  public HomePageEntity getHomePageEntity() {
    return this.homePageEntity;
  }

  public void setHomePageEntity(HomePageEntity homePageEntity) {
    this.homePageEntity = homePageEntity;
  }

  public List<HomePageEntity> getList() {
    return this.list;
  }

  public void setList(List<HomePageEntity> list) {
    this.list = list;
  }

  public InputStream getInputStream() {
    return this.inputStream;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}