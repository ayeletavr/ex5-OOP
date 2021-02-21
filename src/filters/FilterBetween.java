package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters all files that are between (inclusive) the given numbers (in k-bytes). */
public class FilterBetween implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        double lowerBound = BytesConverter.kbToBytes(Double.parseDouble(args[1]));
        double upperBound = BytesConverter.kbToBytes(Double.parseDouble(args[2]));
        for (File file : filesArray) {
            if (file.length() >= lowerBound && file.length() <= upperBound) {
                filteredArray.add(file);
            }
        }
        return filteredArray;
    }
}
