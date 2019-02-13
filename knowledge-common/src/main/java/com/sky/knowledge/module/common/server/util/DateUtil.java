package com.sky.knowledge.module.common.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
  public static String getFormatDateString(String formatStr)
  {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

    String dateStr = sdf.format(calendar.getTime());

    return dateStr;
  }

  public static Date getFormatStrToDate(String formatStr, String dateStr)
  {
    Date time = null;
    if ((formatStr != null) && (dateStr != null)) {
      SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
      try
      {
        time = sdf.parse(dateStr);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    if (time == null) {
      time = Calendar.getInstance().getTime();
    }
    return time;
  }

  public static Date getFormatStrToDate(String formatStrOne, String formatStrTwo, String raEndDate)
  {
    Date time = null;
    if ((formatStrOne != null) && (raEndDate != null)) {
      SimpleDateFormat sdf = new SimpleDateFormat(formatStrOne);
      try
      {
        time = sdf.parse(raEndDate);
      } catch (ParseException e) {
        SimpleDateFormat sdfTwo = new SimpleDateFormat(formatStrOne);
        try {
          time = sdfTwo.parse(raEndDate);
        } catch (ParseException e1) {
          e1.printStackTrace();
        }
        e.printStackTrace();
      }
    }

    return time;
  }

  public static Date getStartToDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(5, 1);
    return cal.getTime();
  }

  public static Date getEndToDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int day = getMonth(cal);
    cal.set(5, day);
    return cal.getTime();
  }

  public static int getMonth(Calendar cal)
  {
    int month = cal.get(2) + 1;
    int year = cal.get(1);
    int data = 0;
    switch (month)
    {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      data = 31;
      break;
    case 2:
      if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
        data = 29; break ;
      }
      data = 28;

      break;
    case 4:
    case 6:
    case 9:
    case 11:
      data = 30;
    }

    label122: return data;
  }

  public static Date changeDateToEndDate(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int day = cal.get(5);
    cal.set(5, day + 1);
    return cal.getTime();
  }
}