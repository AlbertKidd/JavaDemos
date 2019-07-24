package com.kidd.demos.log4j;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * 自定义StrLookup
 * @author Kidd
 */
@Plugin(name = "kidd", category = StrLookup.CATEGORY)
public class LookupDemo implements StrLookup {

    @Override
    public String lookup(String key) {
        switch (key) {
            case "random":
                return LogContext.get();
            default:
                return key;
        }
    }

    @Override
    public String lookup(LogEvent event, String key) {
        return lookup(key);
    }
}
