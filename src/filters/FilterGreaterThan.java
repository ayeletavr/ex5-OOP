package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters files that are strictly greater than the given number of k-bytes. */
public class FilterGreaterThan implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();


    /**
     * Filters files that are strictly greater than the given number of k-bytes.
     * @param filesArray array of files.
     * @param args filter value.
     * @return filtered array.
     */
    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        double lowerBound = BytesConverter.kbToBytes(Double.parseDouble(args[1]));
        for (File file : filesArray) {
            if (file.length() > lowerBound) {
                filteredArray.add(file);
            }
        }
        return filteredArray;
    }
}
