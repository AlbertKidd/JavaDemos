package com.demos.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

/**
 * @author Kidd
 *         CreateTime 2017/10/10.
 */
@Log4j2
public class XstreamDemo {

    @Test
    public void test(){
        XStream xStream = new XStream(new DomDriver("utf-8"));
        Child child = new Child();
        child.setName("name");
        child.setAge(11);
        child.setAddress("address");
        child.setSex("male");
        String s = xStream.toXML(child);
        log.info(s);
    }
}
