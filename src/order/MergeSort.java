package order;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** MergeSort that sorts files. */
public class MergeSort {

    /** Constructor. */
    public MergeSort() {
    }



    /**
     * Merge Sort algorithm for sorting the file array.
     * @param unsortedArray - array to sort
     * @param toCompare - comparing each pair of files is done by the chosen comparator.
     */
    private void mergeSort(ArrayList<File> unsortedArray, Comparator<File> toCompare) {
        if (unsortedArray.size() < 2) {
            return;
        }
        int half = unsortedArray.size() / 2;
        ArrayList<File> array_1 = new ArrayList<File>(unsortedArray.subList(0, half));
        ArrayList<File> array_2 = new ArrayList<File>(unsortedArray.subList(half, unsortedArray.size()));
        mergeSort(array_1, toCompare);
        mergeSort(array_2, toCompare);
        merge(array_1, array_2, unsortedArray, toCompare);
    }

    /**
     * Helper method for mergeSort which merges two arrays while maintaining the order between them
     *
     * @param half_1 array_1 to merge (first half)
     * @param half_2 array_2 to merge (second half)
     * @param array an array to merge array_1 and array_2 into it.
     * @param toCompare comparing each pair of files is done by the chosen comparator.
     * @param <File> sorted array.
     */

    private static <File> void merge(ArrayList<File> half_1, ArrayList<File> half_2,
                                     ArrayList<File> array, Comparator<File> toCompare) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < half_1.size() && j < half_2.size()) {
            if (toCompare.compare(half_1.get(i), half_2.get(j)) < 0) {
                array.set(k++, half_1.get(i++));
            }
            else {
                array.set(k++, half_2.get(j++));
            }
        }
        while (i < half_1.size()) {
            array.set(k++, half_1.get(i++));
        }
        while (j < half_2.size()) {
            array.set(k++, half_2.get(j++));
        }
    }


    /**
     * This method sorts file array as requested in the command file
     * (file merge sort with files comparators and option to inverse).
     * @param fileArray array to sort.
     * @param toCompare comparing each pair of files is done by the chosen comparator.
     * @param inverse if inverse is true we want to sort in the other way.
     * @return sorted array.
     */
    public ArrayList<File> finalMergeSort(ArrayList<File> fileArray, Comparator<File> toCompare, boolean inverse) {
        ArrayList<File> mergedArray = new ArrayList<>(fileArray);
        mergeSort(mergedArray, toCompare);
        if (inverse) {
            Collections.reverse(mergedArray);
        }
        return mergedArray;
    }


}
