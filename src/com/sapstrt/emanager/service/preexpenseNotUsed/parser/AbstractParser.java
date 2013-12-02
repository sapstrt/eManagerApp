package com.sapstrt.emanager.service.preexpenseNotUsed.parser;

/**
 * Created by vvarm1 on 10/4/13.
 */
public abstract class AbstractParser implements Parser {
    public String[] tokenise(String string){
        return string.split(" ");
    }

}
