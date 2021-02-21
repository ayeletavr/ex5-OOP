package filesprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A factory to create arrays of the given files.
 */
public class FilesArrayFactory {

    ArrayList<File> filesArray;
    File sourceDir;


    /**
     * Constructor.
     * Creates a new File instance by converting the given pathname string into an abstract pathname.
     * @param pathname A pathname string.
     */
    FilesArrayFactory(String pathname) {
        sourceDir = new File(pathname);
    }

    /**
     * creates an array with the files to process.
     * @return filesArray
     */
    ArrayList<File> createFilesArray() {
        filesArray = new ArrayList<>();
        File[] files = sourceDir.listFiles();
        assert files != null;
        for (File file: files){
            if (!file.isDirectory())
                filesArray.add(file);
        }
        return filesArray;
    }
}
