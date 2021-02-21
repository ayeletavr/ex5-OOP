package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters all hidden files from file array. */
public class FilterHidden implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    @Override
    /**
     * Filters all hidden files from file array.
     */
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        if (args[1].equals("YES")) {
            for (File file : filesArray) {
                if (file.isHidden()) {
                    filteredArray.add(file);
                }
            }
        }
        else if (args[1].equals("NO")) {
            for (File file : filesArray) {
                if (!file.isHidden()) {
                    filteredArray.add(file);
                }
            }
        }
        return filteredArray;
    }
}
