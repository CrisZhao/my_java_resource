package batchprocessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParamParser {
    
    static Map<PARAM, String> parse(String[] args) {
        Map<PARAM, String> arguments = new HashMap<PARAM, String>();
        PARAM current = null;
        for (String arg : args) {
            if (arg.startsWith("-")) {
                current = PARAM.lookup(arg.substring(1));
                arguments.put(current, null);
                continue;
            }
            if (current == null) {
                throw new RuntimeException("Can't parse argument " + arg);
            }
            arguments.put(current, arg);
            current = null;
        }
        if(!arguments.containsKey(PARAM.HELP)){
            List<String> emptyList = new ArrayList<String>();
            for(PARAM p : PARAM.values()){
                if(!arguments.containsKey(p) && !p.isNullable()){
                    String defVal = p.getDefault(); 
                    if(p == PARAM.DATATYPE){
                        String service = arguments.get(PARAM.SERVICE);
                        defVal = "JSON".equalsIgnoreCase(service) ? "CSV" : "SOAP".equalsIgnoreCase(service) ? "XML" : null;
                    }
                    if(defVal == null){
                        emptyList.add(p.name());
                    }
                    arguments.put(p, defVal);
                }
            }
//            if(!emptyList.isEmpty()){
//                throw new RuntimeException("The arguments " + emptyList.toString() + " must not be empty.");
//            }
        }
        return Collections.unmodifiableMap(arguments);
    }
}
