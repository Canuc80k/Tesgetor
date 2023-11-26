package com.canuc80k.constant;

import com.canuc80k.datastructure.Pair;

public class LanguageConstant {
    public static final Pair[] LANGUAGE = {
        new Pair("C++", ""), 
        new Pair("C++ 11", "-std=c++11"), 
        new Pair("C++ 14", "-std=c++14"), 
        new Pair("C++ 17", "-std=c++17"), 
        new Pair("C++ 2a", "-std=c++2a"), 
        new Pair("C++ 20", "-std=c++20"),
    };
    
    public static String getExecuteCommand(String language) {
        for (int i = 0; i < LANGUAGE.length; i ++)
            if (language.equals(LANGUAGE[i].getFirstElement()))
                return (String)LANGUAGE[i].getSecondElement();
        return "";
    }

    public static String getGeneralLanguage(String language) {
        for (int i = 0; i < 5; i ++)
            if (language.equals(LANGUAGE[i].getFirstElement())) 
                return "C++";
        return "";
    }
}
