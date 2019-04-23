package kozitski.data.task2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
public class FileOutputWriter {
    private static final String FOBIDDEN = "->";
    private static final String TXT_POSTFIX = ".txt";
    private static final String OUTPUT_DIRECTORY_NAME = "output";

    public void writeOutput(Collection<?> collection, String fileName){
        File directory = new File(OUTPUT_DIRECTORY_NAME);

        if (!directory.exists()) {
            try{
                directory.mkdir();
            }
            catch(SecurityException e){
                log.warn("Can not create output directory", e);
            }
        }

        String currentDir = System.getProperty("user.dir");
        File file = new File( currentDir + File.separator + OUTPUT_DIRECTORY_NAME + File.separator +
                changeForbiddenSymbols(fileName) + TXT_POSTFIX);
        log.info("file stored at directory: " + file.getAbsolutePath());

        try (FileWriter fileWriter = new FileWriter(file)){
            collection.forEach(elem -> {
                try {
                    fileWriter.write(elem.toString() + "\n");
                } catch (IOException e1) {
                    log.warn("Current elem wasn't written" + elem, e1);
                }
            });
        }
        catch (IOException e) {
            log.warn("Problem during working with file", e);
        }
    }

    private String changeForbiddenSymbols(String string){
        return string.replace(FOBIDDEN, CommonConstant.OUTPUT_SEPARATOR);
    }

}
