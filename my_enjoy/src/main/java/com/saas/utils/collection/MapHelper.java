package com.saas.utils.collection;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by zacky on 2017/6/20.
 */
public class MapHelper {
    private MapHelper() {
    }

    public static BigDecimal getBigDecimal(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        return o == null?null:(o instanceof BigDecimal?(BigDecimal)o:new BigDecimal(o.toString()));
    }

    public static int getInt(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        return o == null?0:((Number)o).intValue();
    }

    public static long getLong(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        return o == null?0L:((Number)o).longValue();
    }

    public static double getDouble(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        return o == null?0.0D:((Number)o).doubleValue();
    }

    public static float getFloat(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        return o == null?0.0F:((Number)o).floatValue();
    }

    public static String getString(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        return o == null?null:o.toString();
    }

    public static Date getDate(Map<?, ?> map, Object key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        } else if(o instanceof Date) {
            return (Date)o;
        } else {
            String time = o.toString();
            SimpleDateFormat sdf = null;
            if(time.length() > 10) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }

            try {
                return sdf.parse(time);
            } catch (ParseException var6) {
                throw new RuntimeException(var6);
            }
        }
    }
}
