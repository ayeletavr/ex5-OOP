package filesprocessing;

import commandfile.CommandFileProcessor;
import commandfile.Section;
import commandfile.SectionsArrayFactory;
import filters.Filter;
import order.ComparatorsFactory;
import order.MergeSort;
import java.io.File;
import java.util.ArrayList;

/** This class runs the whole program. */
public class DirectoryProcessor {
    private final static String ERROR_PREFIX = "ERROR: ";
    private final static String TOO_MANY_ARGS_ERROR_MSG = "Too many arguments were loaded.";
    private final static String NOT_ENOUGH_ARGS_ERROR_MSG = "Missing arguments.";

    /**
     * Main method for the program.
     * @param args sourceDir, commandFile
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println(ERROR_PREFIX + NOT_ENOUGH_ARGS_ERROR_MSG);
        }
        else if (args.length > 2) {
            System.err.println(ERROR_PREFIX + TOO_MANY_ARGS_ERROR_MSG);
        }
        try {
            run(args[0], args[1]);
        }
        catch (Exception e) {
            System.err.println(ERROR_PREFIX + e);
        }

    }

    /**
     * Runner for the program. runs the processor.
     * @param sourceDir source dir to process.
     * @param commandFile command file to command.
     * @throws Exception
     */
    public static void run(String sourceDir, String commandFile) throws Exception {
        ArrayList<Section> sections = new SectionsArrayFactory().createSectionArray
                (new CommandFileProcessor(commandFile).processCommandFile());
        if (sections.size() == 0) {
            return;
        }
        ArrayList<File> filesToProcess = new FilesArrayFactory(sourceDir).createFilesArray();
        for (Section section : sections) {
            ArrayList<File> finalArray = new Filter().filterFiles(filesToProcess, section.getFilter(), section.getNot());
            finalArray = new MergeSort().finalMergeSort(finalArray, new ComparatorsFactory().createComparator(section.getOrder()), section.getReverse());
            section.printWarnings();
            for (File file : finalArray) {
                System.out.println(file.getName());
            }
        }
    }
}
