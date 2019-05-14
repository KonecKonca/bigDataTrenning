package kozitski.data.converter.runner.arg;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ArgHandler {

    private ApplicationProperties applicationProperties;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void setArgsValues(List<Option> options){

        options.forEach(option -> {
            String argument = option.getValue();
            String shortName = option.getOpt();
            String fullName = option.getLongOpt();

            if(argument != null && !argument.isEmpty()){

                if(ArgType.INPUT_PATH.getShortName().equalsIgnoreCase(shortName) || ArgType.INPUT_PATH.getFullName().equalsIgnoreCase(fullName)){
                    applicationProperties.setInputPath(argument);
                }

                if(ArgType.OUTPUT_PATH.getShortName().equalsIgnoreCase(shortName) || ArgType.OUTPUT_PATH.getFullName().equalsIgnoreCase(fullName)){
                    applicationProperties.setOutputPath(argument);
                }

                if(ArgType.HOST_NAME.getShortName().equalsIgnoreCase(shortName) || ArgType.HOST_NAME.getFullName().equalsIgnoreCase(fullName)){
                    applicationProperties.setHostName(argument);
                }

                if(ArgType.PORT_VALUE.getShortName().equalsIgnoreCase(shortName) || ArgType.PORT_VALUE.getFullName().equalsIgnoreCase(fullName)){
                    applicationProperties.setPortValue(argument);
                }

                if(ArgType.BATCH_SIZE.getShortName().equalsIgnoreCase(shortName) || ArgType.BATCH_SIZE.getFullName().equalsIgnoreCase(fullName)){
                    applicationProperties.setBatchSize(Integer.parseInt(argument));
                }

            }

        });

    }

}
