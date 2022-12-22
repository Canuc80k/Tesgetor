package com.canuc80k.validator;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import com.canuc80k.launcher.GlobalResource;

public class DirectoryValidator {
    public static boolean validateConfigFiles() {
        List<String> paths = GlobalResource.getConfigData();

        for (int i = 0; i < paths.size(); i ++) {
            File file = new File(paths.get(i));
            if (!file.exists()) {
                JOptionPane.showMessageDialog(
                    null, 
                    "One or more configuration files are missing or inaccessible.",
                    "Check your config files",
                    JOptionPane.NO_OPTION
                );
                return false;
            }
        };

        return true;
    }
    public static boolean validateTestcaseFiles() {
        List<String> paths = GlobalResource.getConfigData();
        File testcaseFolder = new File(paths.get(GlobalResource.TESTCASE_FOLDER_INDEX));
        File zipFolder = new File(testcaseFolder.getAbsolutePath() + ".zip");

        if (zipFolder.exists()) {
            JOptionPane.showMessageDialog(
                null, 
                "Testcase folder has already ziped",
                "Use clear to delete the old one first",
                JOptionPane.NO_OPTION
            );
            return false;
        }

        if (!testcaseFolder.exists()) {
            JOptionPane.showMessageDialog(
                null, 
                "Testcase folder is missing or inaccessible.",
                "Check your testcase folder in config files",
                JOptionPane.NO_OPTION
            );
            return false;
        }

        File[] tests = testcaseFolder.listFiles();
        if (tests == null || tests.length == 0) {
            JOptionPane.showMessageDialog(
                null, 
                "Testcase folder is empty",
                "Generate some testcases bro",
                JOptionPane.NO_OPTION
            );
            return false;
        }
        return true;
    }
}