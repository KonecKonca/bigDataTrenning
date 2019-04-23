package kozitski.data.converter.io.parser;

import java.util.List;

public interface InputLineParser<T>{

   T parseLine(String line);

}
