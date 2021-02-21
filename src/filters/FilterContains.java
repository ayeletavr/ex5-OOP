package filters;

import java.io.File;
import java.util.ArrayList;

/** Contains filter - checks if file name contains a specific value, and filters according to this. */
public class FilterContains implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    /**
     * checks if file name contains a specific value, and filters according to this.
     * @param filesArray array of files.
     * @param args filter value.
     * @return filtered array.
     */
    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        for (File file : filesArray) {
            if (file.getName().contains(args[1])) {
                filteredArray.add(file);
            }
        }
        return filteredArray;
    }
}
