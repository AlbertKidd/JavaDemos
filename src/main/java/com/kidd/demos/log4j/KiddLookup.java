package com.kidd.demos.log4j;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

@Plugin(name = "kidd", category = "Lookup")
public class KiddLookup implements StrLookup {
    @Override
    public String lookup(String key) {
        if (key.equals("a")) {
            return "aa";
        }
        if (key.equals("b")) {
            return "bb";
        }
        return null;
    }

    @Override
    public String lookup(LogEvent event, String key) {
        return lookup(key);
    }
}
