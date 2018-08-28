package com.sic777.utils.bean.convert;

import java.util.Date;

import com.sic777.utils.DateFormatTools;
import org.apache.commons.beanutils.Converter;


/**
 * <p>日期转换器
 *
 * @author sic777
 * @since 0.0.1
 */
public class DateConvert implements Converter {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Object convert(Class clz, Object value) {
        if (value instanceof String) {
            //TODO.. 暂时先支持yyyy-MM-dd'T'HH:mm:ss.SS'Z'格式,需要支持其他格式,请修改此处代码,用正则匹配.
            return DateFormatTools.funcGetDate((String) value);
        }
        if (value instanceof Long) {
            Long l = (Long) value;
            return new Date(l);
        }
        return value;
    }

}
