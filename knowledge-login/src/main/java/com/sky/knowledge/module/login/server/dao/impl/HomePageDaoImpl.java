package com.sky.knowledge.module.login.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.framework.server.components.ibatis.IBatis3DaoImpl;
import com.sky.knowledge.module.login.server.dao.IHomePageDao;
import com.sky.knowledge.module.login.shared.domain.HomePageEntity;

@Repository
public class HomePageDaoImpl extends IBatis3DaoImpl
  implements IHomePageDao
{
  public List<HomePageEntity> queryAll(String mapper, HomePageEntity entity)
    throws Exception
  {
    if ((mapper == null) || (mapper.equals(""))) {
      return null;
    }
    return getSqlSession().selectList(mapper, entity);
  }

  public HomePageEntity query(String mapper, HomePageEntity entity)
    throws Exception
  {
    if ((mapper == null) || (mapper.equals(""))) {
      return null;
    }
    Object obj = getSqlSession().selectOne(mapper, entity);
    return ((obj != null) ? (HomePageEntity)obj : null);
  }
}