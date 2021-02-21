package order;

import java.io.File;
import java.util.Comparator;

/**
 * This class holds classes of comparators that will help us to sort files.
 */
public class Comparators {

    /**
     * Absolute name comparator.
     */
    public static class absCompare implements Comparator<File> {
        public absCompare(){
        }

        /**
         * compares two files for their absolute name.
         * @param file1 file to compare
         * @param file2 file to compare
         * @return a negative integer, zero, or a positive integer as the first argument is less than,
         * equal to, or greater than the second.
         */
        @Override
        public int compare(File file1, File file2) {
            return (file1.getAbsolutePath().compareTo(file2.getAbsolutePath()));
        }
    }

    /**
     * file type comparator.
     */
    public static class typeCompare implements Comparator<File> {
        public typeCompare(){
        }

        /**
         * compares two files for their type.
         * @param file1 file to compare
         * @param file2 file to compare
         * @return a negative integer, zero, or a positive integer as the first argument is less than,
         * equal to, or greater than the second.
         */
        @Override
        public int compare(File file1, File file2) {
            if (getFileType(file1).equals(getFileType(file2))) {
               return (file1.getAbsolutePath().compareTo(file2.getAbsolutePath()));
            }
           return  (getFileType(file1).compareTo(getFileType(file2)));
        }

        /**
         * helper for type comprator - gets a file and returns its type (name suffix).
         * @param file a specific file.
         * @return file type (file name suffix).
         */
        private String getFileType(File file) {
            String SEP = "\\.";
            String fileAbs = file.getAbsolutePath();
            String[] splittedFileString = fileAbs.split(SEP);
            return splittedFileString[splittedFileString.length-1];
        }
    }


    /**
     * file's size comparator.
     */
    public static class sizeCompare implements Comparator<File> {
        public sizeCompare(){
        }

        /**
         * compares to files for their size.
         * @param file1 file to compare
         * @param file2 file to compare
         * @return a negative integer, zero, or a positive integer as the first argument is less than,
         * equal to, or greater than the second.
         */
        @Override
        public int compare(File file1, File file2) {
            if (file1.length() == file2.length()) {
                return (file1.getAbsolutePath().compareTo(file2.getAbsolutePath()));
            }
            return Double.compare(file1.length(),file2.length());
        }



    }
}
