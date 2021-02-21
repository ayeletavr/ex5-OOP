package filters;

import java.io.File;
import java.util.ArrayList;

public interface FilterInterface {

    /**
     * An interface of all filters.
     * @param args filter value.
     * @return filtered file array.
     */
    public ArrayList<File> filter(ArrayList<File> filesArray, String[] args);}
