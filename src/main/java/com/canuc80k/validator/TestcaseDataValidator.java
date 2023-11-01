package com.canuc80k.validator;

import javax.swing.JOptionPane;

import com.canuc80k.launcher.GlobalResource;

public class TestcaseDataValidator {
    public static boolean validateTimeout(String timeout) {
        if (timeout.length() == 0) {
            JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Timout can't be ur wallet",
                    "Timeout Error",
                    JOptionPane.NO_OPTION
                );
            return false;
        }
        
        for (int i = 0; i < timeout.length(); i ++)
            if ((timeout.charAt(i) > '9' || timeout.charAt(i) < '0') && timeout.charAt(i) != '-')  {
                JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Don't know what a number look like?",
                    "Timeout Error",
                    JOptionPane.NO_OPTION
                );
                return false;
            }

        if (timeout.length() > 9) {
            JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Bruh, want to be a tester?",
                    "Timeout Error",
                    JOptionPane.NO_OPTION
                );    
            return false;
        }  
        
        if (Integer.parseInt(timeout) < 1) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(),
                "Only serve children over 1 years old",
                "Timeout Error",
                JOptionPane.NO_OPTION
            );
        
            return false;
        }
        
        if (Integer.parseInt(timeout) > 1000) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(),
                "Hate ur computer that much ?",
                "Timeout Error",
                JOptionPane.NO_OPTION
            );        
            return false;
        }

        return true;
    }

    public static boolean validateIndex(String beginIndex, String endIndex) {
        if (beginIndex.length() == 0) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(),
                "Begin index can't be ur wallet",
                "Testcase Index Error",
                JOptionPane.NO_OPTION
            );
            return false;
        }

        if (endIndex.length() == 0) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(),
                "End index can't be ur wallet",
                "Testcase Index Error",
                JOptionPane.NO_OPTION
            );
            return false;
        }

        for (int i = 0; i < beginIndex.length(); i ++)
            if ((beginIndex.charAt(i) > '9' || beginIndex.charAt(i) < '0') && beginIndex.charAt(i) != '-')  {
                JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Don't know what a number look like?",
                    "Testcase Index Error",
                    JOptionPane.NO_OPTION
                );
                return false;
            }

        for (int i = 0; i < endIndex.length(); i ++)
            if ((endIndex.charAt(i) > '9' || endIndex.charAt(i) < '0') && endIndex.charAt(i) != '-') {
                JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Don't know what a number look like?",
                    "Testcase Index Error",
                    JOptionPane.NO_OPTION
                );
                return false;
            }

        if (beginIndex.length() > 9 || endIndex.length() > 9) {
            JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Bruh, want to be a tester?",
                    "Testcase Index Error",
                    JOptionPane.NO_OPTION
                );    
            return false;
        }  

        if (Integer.parseInt(beginIndex) < 0 || Integer.parseInt(endIndex) < 0) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(),
                "Only serve children over 0 years old",
                "Testcase Index Error",
                JOptionPane.NO_OPTION
            );
        
            return false;
        }

        if (Integer.parseInt(beginIndex) > Integer.parseInt(endIndex)) {
            JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Begin index can't be larger than end index",
                    "Testcase Index Error",
                    JOptionPane.NO_OPTION
                );
            return false;
        }

        if (Integer.parseInt(endIndex) - Integer.parseInt(beginIndex) > 1000) {
            JOptionPane.showMessageDialog(
                GlobalResource.getTopDialog(),
                "Hate ur computer that much ?",
                "Testcase Index Error",
                JOptionPane.NO_OPTION
            );        
            return false;
        }
        return true;
    }
}
