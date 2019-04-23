package kozitski.data.task2.runner;

import lombok.Getter;

public enum ArgType {

    /* For loading */
    LOAD("l", "load",true,"define path to input file"),
    GET("g", "get",true,"define month [YYYY-MM] to narrow down the selection"),
    SAVE("s", "save",true,"define saving type [db, txt]"),
    ALL("a", "all",true,"union all necessary arguments [path] [db, txt] [YYYY-MM]"),


    /* For searching */
    SEARCH_MOST_DANGEROUS("s1", "search1", true, "search most dangerous streets [YYYY-mm->YYYY-mm]"),
    SEARCH_AVERAGE_CRIME_VOLUME("s2", "search2", true, "search average crime volume [YYYY-mm->YYYY-mm]"),
    SEARCH_CRIME_BY_OUTCOME_STATUS("s3", "search3", true, "search crime by outcome status [YYYY-mm->YYYY-mm]"),
    SEARCH_STOP_STATISTIC("s4", "search4", true, "search stop statistic [YYYY-mm->YYYY-mm]"),  //  here DATE(not month)
    SEARCH_STREET_LEVEL_SHAPSHOT("s5", "search5", true, "generate street level snapshot [YYYY-mm->YYYY-mm]"), //  here DATE(not month)
    SEARCH_STOP_AND_CRIME_CORRELATION("s6", "search6", true, "search search top and crime correlation [YYYY-mm->YYYY-mm]"),

    /* Additional */
    CLEAR_DB("cd", "clear-db",false,"clear database"),
    HELP("h", "help",false,"reference information about argument");

    ArgType(String shortName, String fullName, boolean hasArgument, String description) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.hasArgument = hasArgument;
        this.description = description;
    }

    @Getter
    private String shortName;
    @Getter
    private String fullName;
    @Getter
    private boolean hasArgument;
    @Getter
    private String description;

}
