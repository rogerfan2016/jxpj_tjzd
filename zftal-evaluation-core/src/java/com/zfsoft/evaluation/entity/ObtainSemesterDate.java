package com.zfsoft.evaluation.entity;

import com.zfsoft.orcus.lang.TimeUtil;
import com.zfsoft.util.base.StringUtil;

public class ObtainSemesterDate {
    /**
     * 学期时间
     * @return
     */
    public static String[] getSchoolTime(String schoolyear, String semester) {
        String[] schooltime = new String[2];
        if (!StringUtil.isEmpty(schoolyear)) {
            if (!StringUtil.isEmpty(semester)) {
                // 上学期
                if ("0".equals(semester)) {
                    schooltime[0] = schoolyear + "-09-01";
                    schooltime[1] = TimeUtil.format(TimeUtil.addDay(TimeUtil.addYear(schoolyear + "-03-01", 1), -1), "yyyy-MM-dd");
                // 下学期
                } else {
                    schooltime[0] = TimeUtil.format(TimeUtil.addYear(schoolyear + "-03-01", 1), "yyyy-MM-dd");
                    schooltime[1] = TimeUtil.format(TimeUtil.addYear(schoolyear + "-08-31", 1), "yyyy-MM-dd");
                }
            } else {
                schooltime[0] = schoolyear + "-09-01";
                schooltime[1] = TimeUtil.format(TimeUtil.addYear(schoolyear + "-08-31", 1), "yyyy-MM-dd");
            }
        }

        return schooltime;
    }
}
