package com.gzmu.tcsys.commmon;

import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author weicaiwang
 */
public class DateFormate implements Converter<String, Date>, Serializable {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Date convert(String source) {

        //实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //转成直接返回
            return simpleDateFormat.parse( source );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果参数绑定失败返回null
        return null;
    }

    public static Date getFormateDate(String stringDate){
        DateFormate dateFormate = new DateFormate();
        return dateFormate.convert( stringDate);
    }

    public static String numDateToDate(Long numFormatDate){
        try {
            Date date = new Date( numFormatDate );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone( TimeZone.getTimeZone( "GMT+8" ) );
            String format = sdf.format( date );
            return format;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
