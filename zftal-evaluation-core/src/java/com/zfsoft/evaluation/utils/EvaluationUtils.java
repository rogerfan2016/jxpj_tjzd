package com.zfsoft.evaluation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ibm.icu.util.Calendar;
import com.zfsoft.orcus.lang.TimeUtil;

/**
 * 
 * @author Administrator
 *
 */
public class EvaluationUtils {

    /**
     * 取得星期
     * @return
     */
    public static String getDayOfWeek(String date) {
        int mark = 0;
        if (isDate(date)) {
            mark = TimeUtil.toCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
        } else {
            mark = Integer.parseInt(date);
        }
        
        if (mark == 1) {
            return "星期一";
        } else if (mark == 2) {
            return "星期二";
        } else if (mark == 3) {
            return "星期三";
        } else if (mark == 4) {
            return "星期四";
        } else if (mark == 5) {
            return "星期五";
        } else if (mark == 6) {
            return "星期六";
        } else if (mark == 7) {
            return "星期日";
        } else {
            return "";
        }
    }
    
    /**
     * 日期判断
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            f.setLenient(false);
            f.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
