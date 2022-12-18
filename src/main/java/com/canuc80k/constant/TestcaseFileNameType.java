package com.canuc80k.constant;

public enum TestcaseFileNameType {
    NORMAL,
    LEXICOGRAPHICAL_ORDER;

    public static String getFileName(TestcaseFileNameType type, int currentTestcaseIndex, int lastTestcaseFileNameLength) {
        if (type == TestcaseFileNameType.NORMAL) return String.valueOf(currentTestcaseIndex);
        if (lastTestcaseFileNameLength == 0) return "0"; 

        String filename = "";
        int currentLength = (int)(Math.log10(currentTestcaseIndex) + 1);
        int amount0prefix = lastTestcaseFileNameLength - currentLength;
        for (int i = 0; i < amount0prefix; i ++) filename += "0";
        filename += String.valueOf(currentTestcaseIndex);

        return filename;
    }
}
