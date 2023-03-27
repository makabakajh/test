package com.jeesite.modules.ws.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.*;
import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Author oqm
 * @Date 2023/3/9 15:25
 * @Description TODO
 * @Version 1.0
 */
public class DateUtils {

    /*
     * @Author oqm
     * @Date 2023/3/9
     * @Param
     * @return
     * @Description 日期转为时间戳
     **/
    public static String dateToStamp(String date){
        String res="";
        //设置时间模版
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time = sdf.parse(date);
            long timestamp = time.getTime() / 1000L;
            return timestamp+res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * @Author oqm
     * @Date 2023/3/9
     * @Param -1 代表当天前一天的日期，如果将 -1 改成 0，得到的会是当前的日期。以此类推  可以用Java得到以当前时间为准的前几天或者后几天的日期
     * @return
     * @Description 获取前几天或后几天的日期
     **/
    public static  String getBeforeDate(Date sourceDate,Integer amount){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 多态
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.DATE, amount);
        sourceDate = calendar.getTime();
        String format = df.format(sourceDate);
        return format;
    }

    public static ArrayList<String> getAllDateByParamDate(String startDate, String endDate, String type) {
        if (!"year".equals(type) && !"month".equals(type) && !"day".equals(type)) {
            return null;
        }
        DateTime parseStartDate = DateUtil.parse(startDate);
        DateTime parseEndDate = DateUtil.parse(endDate);
        // 存储所有日期的list
        ArrayList<String> list = new ArrayList<>();
        // 获取所有年份
        if (type.equals("year")) {
            list.add(parseStartDate.toString("yyyy"));
            DateTime endDateOffset = parseEndDate.offsetNew(DateField.YEAR, -1);
            while (parseStartDate.isBefore(endDateOffset)) {
                DateTime stageDateTime = parseStartDate.offset(DateField.YEAR, 1);
                list.add(stageDateTime.toString("yyyy"));
                parseStartDate = stageDateTime;
            }
        } else if (type.equals("month")) {
            // 获取所有月份
            list.add(parseStartDate.toString("yyyy-MM"));
            DateTime endDateOffset = parseEndDate.offsetNew(DateField.MONTH, -1);
            while (parseStartDate.isBefore(endDateOffset)) {
                DateTime stageDateTime = parseStartDate.offset(DateField.MONTH, 1);
                list.add(stageDateTime.toString("yyyy-MM"));
                parseStartDate = stageDateTime;
            }
            list.add(parseEndDate.toString("yyyy-MM"));
        } else {
            // 获取所有日期
            list.add(parseStartDate.toString("yyyy-MM-dd"));
            DateTime endDateOffset = parseEndDate.offsetNew(DateField.DAY_OF_MONTH, -1);
            while (parseStartDate.isBeforeOrEquals(endDateOffset)) {
                DateTime stageDateTime = parseStartDate.offset(DateField.DAY_OF_MONTH, 1);
                list.add(stageDateTime.toString("yyyy-MM-dd"));
                parseStartDate = stageDateTime;
            }
        }
        // 返回数据
        return list;
    }

    public static void main(String[] args) {
        String s = dateToStamp("2023-03-08 00:00:00");

    }
}
