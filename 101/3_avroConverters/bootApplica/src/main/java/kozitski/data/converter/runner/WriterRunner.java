package kozitski.data.converter.runner;

import kozitski.data.converter.io.writer.AbstractAvroWriter;
import kozitski.data.converter.runner.arg.ArgHandler;
import kozitski.data.converter.runner.arg.ArgType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class WriterRunner {

    private List<AbstractAvroWriter> writerList;
    private ArgHandler argHandler;
    /**
     * Sets writer list.
     *
     * @param writerList the writer list
     */
    @Autowired
    public void setWriterList(List<AbstractAvroWriter> writerList) {
        this.writerList = writerList;
    }

    @Autowired
    public void setArgHandler(ArgHandler argHandler) {
        this.argHandler = argHandler;
    }

    void writeAll(String ... args){
        handleArgs(args);

        writerList.forEach(AbstractAvroWriter::writeRecord);

    }

    private void handleArgs(String ... args){
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        List<ArgType> argTypes = Arrays.asList(ArgType.values());
        argTypes.forEach(arg -> options.addOption(arg.getShortName(), arg.getFullName(), arg.isHasArgument(), arg.getDescription()));

        try {
            CommandLine commandLine = parser.parse(options, args);
            argHandler.setArgsValues(Arrays.asList(commandLine.getOptions()));
        }
        catch (ParseException e){
            System.exit(NumberUtils.INTEGER_ONE);
            log.error("Can not parse command line args", e);
        }

    }

}
