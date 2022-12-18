package filetool;

import java.io.File;

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
}