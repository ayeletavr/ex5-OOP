package filters;

import java.io.File;
import java.util.ArrayList;

/** Filters all files that have execution permission. */
public class FilterExecutable implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    @Override
    /**
     * Filters all files that have execution permission.
     */
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        if (args[1].equals("YES")) {
            for (File file : filesArray) {
                if (file.canExecute()) {
                    filteredArray.add(file);
                }
            }
        }
        else if (args[1].equals("NO")) {
            for (File file : filesArray) {
                if (!file.canExecute()) {
                    filteredArray.add(file);
                }
            }
        }
        return filteredArray;
    }
}
