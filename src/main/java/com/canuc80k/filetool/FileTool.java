package com.canuc80k.filetool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileTool {
	public static synchronized void deleteChildFilesInFolder(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) for (File f : contents) deleteFolder(f);
	}

    public static synchronized void deleteFolder(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) for (File f : contents) deleteFolder(f);
	    file.delete();
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