package com.sky.knowledge.module.common.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil
{
  public static List<Object> jsonStrToList(String jsonString, Class pojoClass)
  {
    JSONArray jsonArray = JSONArray.fromObject(jsonString);

    List list = new ArrayList();
    Map m = new HashMap();
    m.put("items", pojoClass);
    for (int i = 0; i < jsonArray.size(); ++i)
    {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      Object fb = JSONObject.toBean(jsonObject, pojoClass, m);
      list.add(fb);
    }
    return list;
  }

  public static final JSON toJSON(String[] properties, List data, String jsonName)
  {
    JsonConfig config = new JsonConfig();
    config.setIgnoreDefaultExcludes(false);
    config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    config.setExcludes(properties);
    return JSONSerializer.toJSON(data, config);
  }

  public static <T> List<T> toBeanCollection(String jsonString, Class<T> klass)
  {
    List result = new ArrayList();
    JSONArray jsonArray = JSONArray.fromObject(jsonString);
    JSONObject jsonObject = null;
    for (int i = 0; i < jsonArray.size(); ++i)
    {
      jsonObject = jsonArray.getJSONObject(i);

      Object item = JSONObject.toBean(jsonObject, klass);

      result.add(item);
    }

    return result;
  }
}