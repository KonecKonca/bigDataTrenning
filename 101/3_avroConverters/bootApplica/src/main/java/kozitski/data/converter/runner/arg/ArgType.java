package kozitski.data.converter.runner.arg;

import lombok.Getter;

public enum ArgType {

    INPUT_PATH("i", "input",true,"define input file path"),
    OUTPUT_PATH("o", "output",true,"define output file path"),
    HOST_NAME("h", "host",true,"define host name"),
    PORT_VALUE("p", "port",true,"define port value"),
    BATCH_SIZE("b", "batch",true,"define batch size value");

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
