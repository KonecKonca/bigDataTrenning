package com.epam.kozitski.runner;

import lombok.Getter;

public enum ArgType {
    LOAD("l", "load",true,"define path to input file"),
    GET("g", "get",true,"define month [YYYY-MM] to narrow down the selection"),
    SAVE("s", "save",true,"define saving type [db, txt]"),
    ALL("a", "all",true,"union all necessary arguments [path] [db, txt] [YYYY-MM]"),

    CLEAR_DB("cd", "clear-db",false,"clear database"),
    HELP("h", "help",false,"reference information about argument");


    ArgType(String shortName, String fullName, boolean hasArgument, String description) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.hasArgument = hasArgument;
        this.description = description;
    }

    @Getter private String shortName;
    @Getter private String fullName;
    @Getter private boolean hasArgument;
    @Getter private String description;
}
