package com.canuc80k.constant;

public class LanguageConstant {
    public static final String[] LANGUAGE = {"C++", "C++ 11", "C++ 14", "C++ 17", "C++ 2a", "C++ 20"};
    
    public static String getExecuteCommand(String language) {
        if (language.equals(LANGUAGE[0])) return "";
        if (language.equals(LANGUAGE[1])) return "-std=c++11";
        if (language.equals(LANGUAGE[2])) return "-std=c++14";
        if (language.equals(LANGUAGE[3])) return "-std=c++17";
        if (language.equals(LANGUAGE[4])) return "-std=c++2a";
        if (language.equals(LANGUAGE[5])) return "-std=c++20";
        return "";
    }

    public static String getGeneralLanguage(String language) {
        if (language.equals(LANGUAGE[0]) || language.equals(LANGUAGE[1]) || language.equals(LANGUAGE[2]) || 
            language.equals(LANGUAGE[3]) || language.equals(LANGUAGE[4]) || language.equals(LANGUAGE[5])) 
                return "C++";

        return "";
    }
}
