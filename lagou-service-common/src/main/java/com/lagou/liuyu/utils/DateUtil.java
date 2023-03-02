package com.lagou.liuyu.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author LiuYu
 * @date 2019/4/23 20:20
 */
public class DateUtil {

    private DateUtil(){}

    private static final class MeHolder {
        public static final DateUtil me = new DateUtil();
    }

    /**
     * 使用单例模式获取对象，双重锁检查
     *
     * 静态内部类实现单例模式
     * @return 当前类对象
     */
    public static DateUtil getInstance(){
        return MeHolder.me;
    }

    /**
     * 获取当前时间
     */
    public static Date now(){
        return localDateTimeToDate(LocalDateTime.now());
    }

    public static Date instantToDate(Instant time){
        if(null != time){
            return Date.from(time);
        }
        return null;
    }

    public static Instant dateToInstant(Date time){
        if(null != time){
            return time.toInstant();
        }
        return null;
    }

    /**
     * LocaDate时间转换为Date时间
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
        if(null == localDate){
            return null;
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date时间转换为LocaDate时间
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date){
        if(null == date){
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }


    public static Date localDateTimeToDate(LocalDateTime localDate){
        if(null == localDate){
            return null;
        }
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date){
        if(null == date){
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将传入时间加上或减去i个月
     * @param localDate 规定时间
     * @param i 月份
     * @param flag 用于区分加上还是减少月份，true加
     * @return 时间
     */
    public static LocalDate dateToMonthTime(LocalDate localDate, int i, boolean flag){
        if(null == localDate){
            return null;
        }
        if(flag){
            return localDate.plusMonths(i);
        }else{
            return localDate.minusMonths(i);
        }
    }

    /**
     * 将传入时间加上或减去i个周
     * @param localDate 规定时间
     * @param i 周
     * @param flag true+,false-
     * @return 时间
     */
    public static LocalDate dateToWeekTime(LocalDate localDate, int i, boolean flag){
        if(null == localDate){
            return null;
        }
        if(flag){
            return localDate.plusWeeks(i);
        }else{
            return localDate.minusWeeks(i);
        }
    }

    /**
     * 将传入时间加上或减去i天
     * @param localDate 规定时间
     * @param i 天
     * @param flag true +,false -
     * @return 时间
     */
    public static LocalDate dateToDayTime(LocalDate localDate, int i,boolean flag){
        if(null == localDate){
            return null;
        }
        if(flag){
            return localDate.plusDays(i);
        }else{
            return localDate.minusDays(i);
        }
    }

    /**
     * 将传入时间加上或减去i年
     * @param localDate 规定时间
     * @param i 年
     * @param flag true + ,false -
     * @return 时间
     */
    public static LocalDate dateToYearTime(LocalDate localDate, int i, boolean flag){
        if(null == localDate){
            return null;
        }

        if(flag){
            return localDate.plusYears(i);
        }else{
            return localDate.minusYears(i);
        }
    }



}
