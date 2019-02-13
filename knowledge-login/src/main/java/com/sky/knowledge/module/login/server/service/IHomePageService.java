package com.sky.knowledge.module.login.server.service;

import java.util.List;

import com.sky.knowledge.module.login.shared.domain.HomePageEntity;

public abstract interface IHomePageService
{
  public abstract List<HomePageEntity> queryAll(HomePageEntity paramHomePageEntity)
    throws Exception;

  public abstract HomePageEntity query(HomePageEntity paramHomePageEntity)
    throws Exception;
}