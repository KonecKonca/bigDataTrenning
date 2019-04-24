package kozitski.data.converter.io.util;

import java.util.Optional;

/**
 * The type Convert type.
 * common static convert(parse) functions
 */
public class ConvertType {

    private ConvertType() { }

    /**
     * String with nul parse optional.
     *
     * @param str the str
     * @return the optional
     */
    public static Optional<String> stringWithNulParse(String str){
        Optional<String> result = Optional.empty();

        if (str != null && !str.isEmpty()){
            result = Optional.of(str);
        }

        return result;
    }

    /**
     * Int with nul parse optional.
     *
     * @param str the str
     * @return the optional
     */
    public static Optional<Integer> intWithNulParse(String str){
        Optional<Integer> result = Optional.empty();

        if (str != null && !str.isEmpty()){
            result = Optional.of(Integer.parseInt(str));
        }

        return result;
    }

    /**
     * Double with nul parse optional.
     *
     * @param str the str
     * @return the optional
     */
    public static Optional<Double> doubleWithNulParse(String str){
        Optional<Double> result = Optional.empty();

        if (str != null && !str.isEmpty()){
            result = Optional.of(Double.parseDouble(str));
        }

        return result;
    }

    /**
     * Long with nul parse optional.
     *
     * @param str the str
     * @return the optional
     */
    public static Optional<Long> longWithNulParse(String str){
        Optional<Long> result = Optional.empty();

        if (str != null && !str.isEmpty()){
            result = Optional.of(Long.parseLong(str));
        }

        return result;
    }

}
