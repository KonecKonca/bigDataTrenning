package kozitski.data.converter.io.parser;

/**
 * The interface Input line parser.
 *
 * @param <T> the type parameter
 */
public interface InputLineParser<T>{

   /**
    * Parse line t.
    *
    * @param line the line
    * @return the t
    */
   T parseLine(String line);

}
