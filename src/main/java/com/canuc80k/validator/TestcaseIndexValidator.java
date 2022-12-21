package com.canuc80k.validator;

import javax.swing.JOptionPane;

public class TestcaseIndexValidator {
    public static boolean validate(String beginIndex, String endIndex) {
        if (beginIndex.length() == 0) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Begin index can't be ur wallet",
                    "R u dumb?",
                    JOptionPane.NO_OPTION
                );
            
            return false;
        }

        if (endIndex.length() == 0) {
            JOptionPane.showMessageDialog(
                    null, 
                    "End index can't be ur wallet",
                    "R u dumb?",
                    JOptionPane.NO_OPTION
                );
            
            return false;
        }

        for (int i = 0; i < beginIndex.length(); i ++)
            if ((beginIndex.charAt(i) > '9' || beginIndex.charAt(i) < '0') && beginIndex.charAt(i) != '-')  {
                JOptionPane.showMessageDialog(
                    null, 
                    "Don't know what a number look like?",
                    "R u dumb?",
                    JOptionPane.NO_OPTION
                );
                return false;
            }

        for (int i = 0; i < endIndex.length(); i ++)
            if ((endIndex.charAt(i) > '9' || endIndex.charAt(i) < '0') && endIndex.charAt(i) != '-') {
                JOptionPane.showMessageDialog(
                    null, 
                    "Don't know what a number look like?",
                    "R u dumb?",
                    JOptionPane.NO_OPTION
                );
                return false;
            }

        if (beginIndex.length() > 9 || endIndex.length() > 9) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Bruh, want to be a tester?",
                    "R u dumb?",
                    JOptionPane.NO_OPTION
                );
            
            return false;
        }  

        if (Integer.parseInt(beginIndex) < 0 || Integer.parseInt(endIndex) < 0) {
            JOptionPane.showMessageDialog(
                null, 
                "Only serve children over 0 years old",
                "R u dumb?",
                JOptionPane.NO_OPTION
            );
        
            return false;
        }

        if (Integer.parseInt(beginIndex) > Integer.parseInt(endIndex)) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Begin index can't be larger than end index",
                    "R u dumb?",
                    JOptionPane.NO_OPTION
                );
            
            return false;
        }

        if (Integer.parseInt(endIndex) - Integer.parseInt(beginIndex) > 1000) {
            JOptionPane.showMessageDialog(
                null, 
                "Hate ur computer that much ?",
                "Coldhearted",
                JOptionPane.NO_OPTION
            );
        
            return false;
        }
        return true;
    }
}
