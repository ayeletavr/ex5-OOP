package commandfile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** A factory that creates sections. */
public class SectionFactory {

    private final static String WARNING_MSG = "Warning in line ";
    private final static String LEGAL_ALL_FILTER = "all";
    private final static String LEGAL_BETWEEN_FILTER = "between";
    private final static String[] LEGAL_DOUBLE_FILTER = {"greater_than", "smaller_than"};
    private final static String[] LEGAL_STRING_FILTERS = {"file", "contains", "prefix", "suffix"};
    private final static String[] LEGAL_BOOLEANS_FILTERS = {"writable", "executable", "hidden"};
    private final static String[] LEGAL_BOOLEANS = {"YES", "NO"};
    private final static String[] LEGAL_ORDERS = {"abs", "type", "size"};
    private final static String LEGAL_NOT = "NOT";
    private final static String LEGAL_REVERSE = "REVERSE";
    private final static String SEPERATOR = "#";

    // Methods that checks validity of input filter/order.

    /**
     * checks validity of "all" filter (helper for checkValidityOfFilter)
     * @param filter to check.
     * @return true if valid, else false.
     */
    private boolean checkValidityOfAllFilter(String[] filter){
        if (filter.length == 1) {
            return true;
        }
        return filter.length == 2 && filter[1].equals(LEGAL_NOT);
    }

    /**
     * checks validity of "between" filter (helper for checkValidityOfFilter)
     * @param filter to check.
     * @return true if valid, else false.
     */
    private boolean checkValidityOfBetweenFilter(String[] filter) {
        if (filter.length == 3 && Double.parseDouble(filter[1]) <= Double.parseDouble(filter[2]) && Double.parseDouble(filter[1]) > -1) {
            return true;
        }
        return filter.length == 4 && Double.parseDouble(filter[1]) <= Double.parseDouble(filter[2]) &&
                Double.parseDouble(filter[1]) > -1 &&
                filter[3].equals(LEGAL_NOT);
    }

    /**
     * checks validity of "greater_than" and "smaller_than" filters (helper for checkValidityOfFilter)
     * @param filter to check
     * @return true if valid, else false.
     */
    private boolean checkValidityOfDoubleFilter(String[] filter) {
        if (filter.length == 2 && Double.parseDouble(filter[1]) > -1) {
            return true;
        }
        return filter.length == 3 && Double.parseDouble(filter[1]) > -1 && filter[2].equals(LEGAL_NOT);
    }

    /**
     * checks validity of "file", "contains", "prefix" and "suffix" filters (helper for checkValidityOfFilter)
     * @param filter to check
     * @return true if valid, else false.
     */
    private boolean checkValidityOfStringFilter(String[] filter) {
        return (filter.length == 2 || (filter.length == 3 && filter[2].equals(LEGAL_NOT)));
    }

    /**
     * checks validity of "writable", "executable" and "hidden" filters (helper for checkValidityOfFilter)
     * @param filter to check
     * @return true if valid, else false.
     */
    private boolean checkValidityOfBooleanFilter(String[] filter){
        if (filter.length == 2 && Arrays.asList(LEGAL_BOOLEANS).contains(filter[1])) {
            return true;
        }
        return filter.length == 3 && Arrays.asList(LEGAL_BOOLEANS).contains(filter[1]) && filter[2].equals(LEGAL_NOT);
    }

    /**
     * gets a filter (in a string array) and checks if it is valid.
     * @param filter to check
     * @return true if valid, else false.
     */
    private boolean checkValidityOfFilter(String[] filter) {
        if (filter[0].equals(LEGAL_ALL_FILTER)) {
            if (checkValidityOfAllFilter(filter)) {
                return true;
            }
        }
        else if (filter[0].equals(LEGAL_BETWEEN_FILTER)) {
            if (checkValidityOfBetweenFilter(filter)) {
                return true;
            }
        }
        else if (Arrays.asList(LEGAL_DOUBLE_FILTER).contains(filter[0])) {
            if (checkValidityOfDoubleFilter(filter)) {
                return true;
            }
        }
        else if (Arrays.asList(LEGAL_STRING_FILTERS).contains(filter[0])) {
            if (checkValidityOfStringFilter(filter)) {
                return true;
            }
        }
        else if (Arrays.asList(LEGAL_BOOLEANS_FILTERS).contains(filter[0])) {
            if (checkValidityOfBooleanFilter(filter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets an order (in a string array) and checks if it is valid.
     * @param order to check.
     * @return true if valid, else false.
     */
    private boolean checkValidityOfOrder(String[] order) {
        if (Arrays.asList(LEGAL_ORDERS).contains(order[0])) {
            if (order.length == 1 || order.length == 2) {
                return true;
            }
            return order.length == 3 && order[2].equals(LEGAL_REVERSE);
        }
        return false;
    }

    /**
     * Gets an array of strings, and if its valid, it creates a suitable section.
     * @param stringArrayList an array of strings contains the processed information in the command file.
     * @param commandLineIndex in one array, there are two commands (filter and order).
     *                        this parameter points the index of the curr command line.
     * @return section.
     */
    public Section createSection(List<String> stringArrayList, int commandLineIndex) {
        Section section = new Section();
        String[] filter = stringArrayList.get(1).split(SEPERATOR);
        if (checkValidityOfFilter(filter)) {
            if (Collections.singletonList(LEGAL_NOT).contains(filter[filter.length - 1])) {
                section.setNot(true);
            }
            section.setFilter(filter); }
        else {
            section.setWarning(WARNING_MSG + (commandLineIndex + 1));
        }
        if (stringArrayList.size() == 4 && checkValidityOfOrder(stringArrayList.get(3).split(SEPERATOR))) {
            String[] order = stringArrayList.get(3).split(SEPERATOR);
            if (order.length == 2) {
                section.setReverse(true);
            }
            section.setOrder(order[0]);
        }
        else {
            if (stringArrayList.size() != 3) {
                section.setWarning(WARNING_MSG + (commandLineIndex + 3));
            }
        }
        return section;
    }
}
