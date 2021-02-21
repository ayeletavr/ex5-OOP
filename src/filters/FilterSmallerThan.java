package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters files that are strictly less than the given number of k-bytes. */
public class FilterSmallerThan implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    /**
     * Filters files that are strictly less than the given number of k-bytes.
     * @param filesArray array of files.
     * @param args filter value.
     * @return
     */
    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        double upperBound = BytesConverter.kbToBytes(Double.parseDouble(args[1]));
        for (File file : filesArray) {
            if (file.length() < upperBound) {
                filteredArray.add(file);
            }
        }
        return filteredArray;

    }
}
