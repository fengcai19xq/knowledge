package com.sky.knowledge.module.login.server.dao;

import java.util.List;

import com.sky.knowledge.module.login.shared.domain.HomePageEntity;

public abstract interface IHomePageDao
{
  public abstract List<HomePageEntity> queryAll(String paramString, HomePageEntity paramHomePageEntity)
    throws Exception;

  public abstract HomePageEntity query(String paramString, HomePageEntity paramHomePageEntity)
    throws Exception;
}