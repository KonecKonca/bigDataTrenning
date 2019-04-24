package kozitski.data.converter.io.util;


import kozitski.data.converter.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConvertTypeTest {

    @Test(expectedExceptions = NumberFormatException.class)
    public void intWithNulParse() {
        ConvertType.intWithNulParse("3g43");
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void doubleWithNulParse() {
        ConvertType.intWithNulParse("34g3");
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void longWithNulParse() {
        ConvertType.intWithNulParse("34g3");
    }

}
