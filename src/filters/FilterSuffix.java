package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters by suffix. */
public class FilterSuffix implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    /**
     * Filters by suffix.
     * @param filesArray file array.
     * @param args filter value.
     * @return filtered file array.
     */
    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        String suffix = args[1];
        for (File file : filesArray) {
            if (file.getName().endsWith(suffix)) {
                filteredArray.add(file);
            }
        }
        return filteredArray;
    }
}
