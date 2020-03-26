package com.kidd.demos.lombok;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.intellij.lang.annotations.MagicConstant;

import javax.xml.bind.annotation.XmlEnum;

/**
 * @author Kidd
 */
public class LombokDemo {

    @Value(staticConstructor = "of")
    static class ValueDemo{
        private String field1;
        private int field2;
    }

    public static void main(String[] args) {
        ValueDemo valueDemo = ValueDemo.of("f1", 2);
    }
}
