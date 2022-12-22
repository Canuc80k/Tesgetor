package com.canuc80k.filetool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileTool {
    public static boolean DELETE_CURRENT_FOLDER = true;
    public static boolean KEEP_CURRENT_FOLDER = false;
    
    public static synchronized void deleteFolder(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) for (File f : contents) deleteFolder(f);
	    file.delete();
	}

	public static synchronized void deleteFolder(File file, boolean isDeleteCurrentFolder) {
	    File[] contents = file.listFiles();
	    if (contents != null) for (File f : contents) deleteFolder(f);
	    if (isDeleteCurrentFolder) file.delete();
	}

    public static File inputStreamtoFile(InputStream inputStream, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            IOUtils.copy(inputStream, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}