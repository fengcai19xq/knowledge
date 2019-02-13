package com.sky.knowledge.module.login.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.login.server.action.HomePageAction;
import com.sky.knowledge.module.login.server.dao.IHomePageDao;
import com.sky.knowledge.module.login.server.service.IHomePageService;
import com.sky.knowledge.module.login.shared.domain.HomePageEntity;


@Service
public class HomePageServiceImpl
  implements IHomePageService
{
  private Logger log = Logger.getLogger(HomePageAction.class.getName());

  private final String MAPPER_HOME_PAGE = HomePageEntity.class.getName() + 
    ".";

  @Resource
  private IHomePageDao homePageDao;

  public List<HomePageEntity> queryAll(HomePageEntity entity) throws Exception
  {
    if (entity == null) {
      entity = new HomePageEntity();
    }

    Object obj = UserContext.getCurrentUser();
    if (obj == null) {
      return null;
    }
    User user = (User)obj;

    entity.setEmpCode(user.getEmpCode().getEmpCode());
    return this.homePageDao.queryAll(this.MAPPER_HOME_PAGE + "queryAll", 
      entity);
  }

  public HomePageEntity query(HomePageEntity entity)
    throws Exception
  {
    if (entity == null) {
      return null;
    }
    return this.homePageDao.query(this.MAPPER_HOME_PAGE + "query", entity);
  }
}