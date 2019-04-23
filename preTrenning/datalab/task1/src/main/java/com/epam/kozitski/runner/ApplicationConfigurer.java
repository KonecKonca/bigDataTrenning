package com.epam.kozitski.runner;

import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationConfigurer {

    private ArgHandler argHandler;
    @Autowired
    public void setArgHandler(ArgHandler argHandler) {
        this.argHandler = argHandler;
    }

    public void run(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption(ArgType.LOAD.getShortName(), ArgType.LOAD.getFullName(), ArgType.LOAD.isHasArgument(), ArgType.LOAD.getDescription());
        options.addOption(ArgType.GET.getShortName(), ArgType.GET.getFullName(), ArgType.GET.isHasArgument(), ArgType.GET.getDescription());
        options.addOption(ArgType.SAVE.getShortName(), ArgType.SAVE.getFullName(), ArgType.SAVE.isHasArgument(), ArgType.SAVE.getDescription());
        options.addOption(ArgType.ALL.getShortName(), ArgType.ALL.getFullName(), ArgType.ALL.isHasArgument(), ArgType.ALL.getDescription());

        options.addOption(ArgType.CLEAR_DB.getShortName(), ArgType.CLEAR_DB.getFullName(), ArgType.CLEAR_DB.isHasArgument(), ArgType.CLEAR_DB.getDescription());
        options.addOption(ArgType.HELP.getShortName(), ArgType.HELP.getFullName(), ArgType.HELP.isHasArgument(), ArgType.HELP.getDescription());

        CommandLine commandLine = parser.parse(options, args);

        argHandler.iterate(Arrays.asList(commandLine.getOptions()), options);
    }

}
