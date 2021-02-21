package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters by file's name prefix. */
public class FilterPrefix implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    /**
     * Filters by file's name prefix.
     * @param filesArray array of files.
     * @param args filter value.
     * @return filtered array.
     */
    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        String prefix = args[1];
        for (File file : filesArray) {
            if (file.getName().startsWith(prefix)) {
                filteredArray.add(file);
            }
        }
        return filteredArray;
    }
}
