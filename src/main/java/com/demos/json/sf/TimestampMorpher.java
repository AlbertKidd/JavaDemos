package com.demos.json.sf;

import net.sf.ezmorph.object.AbstractObjectMorpher;

import java.sql.Timestamp;

/**
 * @author Kidd
 *         CreateTime 2017/8/15.
 */
public class TimestampMorpher extends AbstractObjectMorpher {

    public Class morphsTo() {
        return Timestamp.class;
    }

    public Object morph(Object o) {
        if (o == null){
            return null;
        }else if (Timestamp.class.isAssignableFrom(o.getClass())){
            return o;
        }else if (!String.class.isAssignableFrom(o.getClass())){
            throw new RuntimeException("不支持的类型！");
        }else {
            String s = (String) o;
            return Timestamp.valueOf(s);
        }
    }
}
