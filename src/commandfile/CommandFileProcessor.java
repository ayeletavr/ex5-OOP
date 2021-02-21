package commandfile;

import exceptions.MissingFilterSubSectionException;
import exceptions.MissingOrderSubSectionException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/** This class reads a command file and processes it to a valid Array of strings. */
public class CommandFileProcessor {

    private Scanner reader;
    private final String FILTER_LINE = "FILTER";
    private final String ORDER_LINE = "ORDER";
    private final String FLAG = "FLAG";

    /**
     * Constructor.
     * @param commandFile the command file path.
     */
    public CommandFileProcessor(String commandFile) throws Exception {
        reader = new Scanner(new File(commandFile));
    }

    /**
     * reads a file and puts its content in a string array list.
     * @return ArrayList of Strings
     */
    public ArrayList<String> createStringArray() throws Exception {
        ArrayList<String> stringsFromFile = new ArrayList<>();
        while (reader.hasNext()) {
            stringsFromFile.add(reader.nextLine());
        }
        return stringsFromFile;
    }

    /**
     * initial processing the string array - finds duplicate FILTER and ORDER lines, and replace it with a flag (string).
     * @param stringArrayList string array list with potential duplicates.
     * @return noDuplicates array.
     */
    public ArrayList<String> flagDuplicates(ArrayList<String> stringArrayList) {
        ArrayList<String> noDuplicatesArray = new ArrayList<>();
        if (stringArrayList.size() == 0) {
            return noDuplicatesArray;
        }
        noDuplicatesArray.add(stringArrayList.get(0)); //if file structure is legal, it is supposed to be FILTER or ORDER.
        for (int i=1; i < stringArrayList.size(); i++) {
            // checking duplicates of FILTER
            if (stringArrayList.get(i).equals(FILTER_LINE) && stringArrayList.get(i-1).equals(FILTER_LINE)){
                noDuplicatesArray.add(FLAG);
                continue;
            }
            // checking duplicates of ORDER
            if (stringArrayList.get(i).equals(ORDER_LINE) && (noDuplicatesArray.get(i-1).equals(FILTER_LINE) ||noDuplicatesArray.get(i-1).equals(ORDER_LINE))) {
                noDuplicatesArray.add(FLAG);
                continue;
            }
            noDuplicatesArray.add(stringArrayList.get(i));
        }
        return noDuplicatesArray;
    }

    /**
     * This method gets an arrayList of String and verifies that it is a valid commands array.
     * @param commandArrayList the array to check.
     * @throws Exception if there is a filter or order that are missing.
     */
    public void verifyValidityOfArray(ArrayList<String> commandArrayList) throws Exception {
        int start_i = 0;
        int curr_i;
        if (!commandArrayList.get(0).equals(FILTER_LINE)) {
            throw new MissingFilterSubSectionException();
        }
        if (!commandArrayList.get(2).equals(ORDER_LINE)) {
            throw new MissingOrderSubSectionException();
        }
        for (int i = 1; i < commandArrayList.size(); i++) {
            if (FILTER_LINE.equals(commandArrayList.get(i))) {
                curr_i = i;
                int MAX_SECTION_LENGTH = 4;
                if ((curr_i - start_i) > MAX_SECTION_LENGTH) {
                    throw new MissingFilterSubSectionException();
                }
                int FILTER_TO_ORDER_DISTANCE = 2;
                if (!ORDER_LINE.equals(commandArrayList.get(curr_i + FILTER_TO_ORDER_DISTANCE))) {
                    throw new MissingOrderSubSectionException();
                }
                start_i = curr_i;
            }
        }
    }

    /**
     * This method combines all processes implemented in the other methods in this class, and returns a final legal
     * array of commands.
     * @return String ArrayList.
     * @throws Exception if there is a filter or order that are missing.
     */
    public ArrayList<String> processCommandFile() throws Exception {
        ArrayList<String> processedArray = flagDuplicates(createStringArray());
        verifyValidityOfArray(processedArray);
        return processedArray;
    }

}
