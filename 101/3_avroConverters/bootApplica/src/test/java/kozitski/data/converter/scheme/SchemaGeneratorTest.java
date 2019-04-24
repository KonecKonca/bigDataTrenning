package kozitski.data.converter.scheme;

import kozitski.data.converter.config.ApplicationConfig;
import kozitski.data.converter.runner.ApplicationExecutor;
import kozitski.data.converter.scheme.impl.DestinationSchemaGenerator;
import kozitski.data.converter.scheme.impl.SampleSubmissionSchemaGenerator;
import kozitski.data.converter.scheme.impl.TestSchemaGenerator;
import kozitski.data.converter.scheme.impl.TrainSchemaGenerator;
import org.apache.avro.Schema;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SchemaGeneratorTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeClass
    public void init(){
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }

    @Test(dataProvider = "schemaGenerator")
    public void isSchemaGeneratedTest(boolean actual, SchemaGenerator schemaGenerator){
        Schema schema = schemaGenerator.generateSchema();

        boolean expected = schema != null;

        Assert.assertEquals(actual, expected);

    }

    @DataProvider(name = "schemaGenerator")
    public Object[][] schemaGenerator(){
        return new Object[][]{
            {true, applicationContext.getBean(DestinationSchemaGenerator.class)},
            {true, applicationContext.getBean(SampleSubmissionSchemaGenerator.class)},
            {true, applicationContext.getBean(TestSchemaGenerator.class)},
            {true, applicationContext.getBean(TrainSchemaGenerator.class)}
        };
    }

}
