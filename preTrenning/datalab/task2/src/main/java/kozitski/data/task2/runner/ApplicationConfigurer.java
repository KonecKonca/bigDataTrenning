package kozitski.data.task2.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ApplicationConfigurer {

    private CommonArgHandler commonArgHandler;
    @Autowired
    public void setCommonArgHandler(CommonArgHandler commonArgHandler) {
        this.commonArgHandler = commonArgHandler;
    }

    public void run(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        List<ArgType> argTypes = Arrays.asList(ArgType.values());
        argTypes.forEach(arg -> options.addOption(arg.getShortName(), arg.getFullName(), arg.isHasArgument(), arg.getDescription()));

        try {
            CommandLine commandLine = parser.parse(options, args);
            commonArgHandler.iterate(Arrays.asList(commandLine.getOptions()), options);
        }
        catch (ParseException e) {
            log.error("Can not parse command line args", e);
        }

    }

}
