package order;

import java.io.File;
import java.util.Comparator;

public class ComparatorsFactory {
    public ComparatorsFactory(){
    }

    /** A factory for comparators. */
    public Comparator<File> createComparator(String order) {
        if (order.equals("abs")) {
            return new Comparators.absCompare();
        }
        if (order.equals("type")) {
            return new Comparators.typeCompare();
        }
        if (order.equals("size")) {
            return new Comparators.sizeCompare();
        }
        return new Comparators.absCompare();
    }
}
