package filters;

import java.io.File;
import java.util.ArrayList;

public class FilterAll implements FilterInterface {

    @Override
    /**
     * In this filter, all files are matched.
     */
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args) {
        return filesArray;
    }
}
