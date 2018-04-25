package com.kidd.demos.log4j;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

@Plugin(name = "kidd", category = StrLookup.CATEGORY)
public class KiddLookup implements StrLookup {

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
