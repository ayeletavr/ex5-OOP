package filters;

import java.io.File;
import java.util.ArrayList;

/** filters all files that file's name is equal to the given arg. */
public class FilterFile implements FilterInterface {

    private ArrayList<File> filteredArray = new ArrayList<>();

    /**
     * filters all files that file's name is equal to the given arg.
     * @param filesArray array of files.
     * @param args filter value.
     * @return filtered array.
     */
    @Override
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        for (File file : filesArray) {
            if (file.getName().equals(args[1])) {
                filteredArray.add(file);
            }
        }
        return filteredArray;
    }
}
