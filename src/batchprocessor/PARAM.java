package batchprocessor;

import java.util.HashMap;
import java.util.Map;

enum PARAM {
    HOST("localhost", "", false), 
    PORT("8080", "", false), 
    SERVICE("JSON", "", false), 
    USER("raiyun", "u", false), 
    PASSWORD("password", "p", false), 
    FILE(null, "f", false), 
    ACTIONTYPE("Persist", "a", false), 
    DATATYPE(null, "d", false), 
    PERSISTYPE("Update", "t", false), 
    SKIPONFAIL("false", "s", false), 
    OUTPUT(null, "o", true),
    HELP(null, "?", true),
    DOWNLOADACTIONTYPE("DOWNLOAD", "a", false), 
    DATASOURCE("MARKET_DATA", "d", false), 
    DOWNLOADOUTPUT("CSV", "o", false);
    
    private final String defaultValue;
    private final String shortName;
    private final boolean nullable;

    PARAM(String defaultValue, String shortName, boolean nullable) {
        this.defaultValue = defaultValue;
        this.shortName = shortName;
        this.nullable = nullable;
    }

    public String getDefault() {
        return this.defaultValue;
    }

    public String getShortName() {
        return this.shortName;
    }

    public boolean isNullable() {
        return this.nullable;
    }

    private static Map<String, PARAM> lookupTable = new HashMap<String, PARAM>();
    static {
        for (PARAM in : PARAM.values()) {
            if (in.getShortName() != null && !in.getShortName().isEmpty()) {
                lookupTable.put(in.getShortName(), in);
            }
        }
    };

    public static PARAM lookup(String name) {
        return lookupTable.containsKey(name) ? lookupTable.get(name)
                : PARAM.valueOf(name.toUpperCase());
    }
}