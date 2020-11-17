package cn.electricdog.noveltools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Sruifeng
 * @Email: suruifeng@wistronits.com
 * @Date: 2020/11/17 15:06
 */
public class DateTools {
    public static boolean isValidDate(String str, String pattern) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }


    public static boolean isValidDate(String str) {
        return isValidDate(str, "yyyy-MM-dd");
    }

    public static String date2Str(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd");
    }

    public static Date str2Date(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date str2Date(String dateStr) {
        return str2Date(dateStr, "yyyy-MM-dd");
    }

    public static String formatDateStr(String dateStr, String pattern) {
        if (isValidDate(dateStr)) {
            return date2Str(str2Date(dateStr, pattern), pattern);
        }
        return null;
    }

    public static String formatDateStr(String dateStr) {
        return formatDateStr(dateStr, "yyyy-MM-dd");
    }


    public static void main(String[] args) {
        System.out.println(isValidDate("2020-02-02"));
        System.out.println(date2Str(new Date(),"yyyy-MM-dd"));
    }
}
