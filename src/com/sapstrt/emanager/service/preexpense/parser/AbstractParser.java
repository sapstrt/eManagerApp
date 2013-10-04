package com.sapstrt.emanager.service.preexpense.parser;

import java.util.Map;

/**
 * Created by vvarm1 on 10/4/13.
 */
public abstract class AbstractParser implements Parser {
    public String[] tokenise(String string){
        return string.split(" ");
    }

}
