package commandfile;


import java.util.ArrayList;
import java.util.List;

/** A factory that creates an array of sections, from arrays of strings, possibly containing a few sections. */
public class SectionsArrayFactory extends SectionFactory {

    private final static String FILTER_LINE = "FILTER";
    private final static String ORDER_LINE = "ORDER";

    /**
     * This method is a factory of sections array.
     * @param stringArrayList String arrayList that already checked for validity.
     * @return Section ArrayList.
     */
    public ArrayList<Section> createSectionArray(ArrayList<String> stringArrayList) {
        ArrayList<Section> sectionArrayList = new ArrayList<>();
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (FILTER_LINE.equals(stringArrayList.get(i))) {
                if (stringArrayList.size() > i + 3) {
                    if (FILTER_LINE.equals(stringArrayList.get(i + 3))) {
                        sectionArrayList.add(createSection(stringArrayList.subList(i, i + 3), i + 1));
                    }
                    else {
                        sectionArrayList.add(createSection(new ArrayList<>(stringArrayList.subList(i,
                                i + 4)), i + 1));
                    }
                }
                else {
                    sectionArrayList.add(createSection(stringArrayList.subList(i, i + 3), i + 1));
                }
            }
        }
        return sectionArrayList;
    }

}
