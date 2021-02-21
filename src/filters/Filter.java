package filters;

import filters.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** This class filters files. */
public class Filter {

    HashMap<String, FilterInterface> filters = new HashMap<>();

    public Filter(){
        filters.put("greater_than", new FilterGreaterThan());
        filters.put("between", new FilterBetween());
        filters.put("smaller_than", new FilterSmallerThan());
        filters.put("file", new FilterFile());
        filters.put("contains", new FilterContains());
        filters.put("prefix", new FilterPrefix());
        filters.put("suffix", new FilterSuffix());
        filters.put("writable", new FilterWritable());
        filters.put("executable", new FilterExecutable());
        filters.put("hidden", new FilterHidden());
        filters.put("all", new FilterAll());
    }

    /**
     * This method removes (filters) requested files from a file array (helper to filterFiles).
     * @param unfilteredArray the array to filter.
     * @param toDelete files to remove from the unfiltered files array.
     * @return filtered array (after removing).
     */
    private ArrayList<File> removeFromFileArray(ArrayList<File> unfilteredArray, ArrayList<File> toDelete) {
        ArrayList<File> filteredArray = new ArrayList<>();
        for (File file : unfilteredArray) {
            if (toDelete.contains(file)) {
                continue;
            }
            filteredArray.add(file);
        }
        return filteredArray;
    }

    public ArrayList<File> filterFiles(ArrayList<File> unfilteredArray, String[] filter, boolean not) {
        ArrayList<File> filteredArray = new ArrayList<>();
        if (filters.containsKey(filter[0])) {
            filteredArray = filters.get(filter[0]).filter(unfilteredArray, filter);
        }
        return not ? removeFromFileArray(unfilteredArray, filteredArray) : filteredArray; ////
    }

}
