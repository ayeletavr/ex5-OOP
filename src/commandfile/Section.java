package commandfile;

import java.util.ArrayList;

/** This class represent a section of commands - filter and order. */
public class Section {

    private String[] DEFAULT_FILTER = {"all"};
    private String DEFAULT_ORDER = "abs";
    private String[] filter;
    private String order;
    private Boolean not;
    private Boolean reverse;
    private ArrayList<String> warnings;


    /**
     * Constructor.
     */
    Section(){
        this.filter = DEFAULT_FILTER;
        this.order = DEFAULT_ORDER;
        this.not = false;
        this.reverse = false;
        this.warnings = new ArrayList<>();
    }

    /** filter getter. */
    public String[] getFilter(){return this.filter;};
    /** order getter. */
    public String getOrder() {return this.order;};
    /** not value getter. */
    public Boolean getNot() {return this.not;};
    /** reverse value setter. */
    public Boolean getReverse() {return this.reverse;};

    /** Filter setter. */
    public void setFilter(String[] filter){
        this.filter = filter;
    }
    /** order setter. */
    public void setOrder(String order){
        this.order = order;
    }
    /** Not value setter. */
    public void setNot(Boolean not){
        this.not = not;
    }
    /** Reverse value setter. */
    public void setReverse(Boolean reverse){
        this.reverse = reverse;
    }
    /** Adds a warning to warning arrayList. */
    public void setWarning(String warning) {this.warnings.add(warning);}

    /** Prints all warning that the section holds in the warning arrayList. */
    public void printWarnings(){
        for (String warning : warnings) {
            System.err.println(warning);
        }
    }


}
