package kozitski.data.converter.runner;

import kozitski.data.converter.config.ApplicationConfig;
import kozitski.data.converter.runner.ApplicationExecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.List;

public class ApplicationExecutorTest {
    private static final String LIST_WRITERS_FIELD_NAME = "writerList";
    private static final int EXPECTED_LIST_WRITERS_NUMBER = 4;

    @Test
    public void numberOfWriterTest() throws NoSuchFieldException, IllegalAccessException {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ApplicationExecutor applicationExecutor = annotationConfigApplicationContext.getBean(ApplicationExecutor.class);

        Class<? extends ApplicationExecutor> clazz = applicationExecutor.getClass();
        Field writerList = clazz.getDeclaredField(LIST_WRITERS_FIELD_NAME);
        writerList.setAccessible(true);

        List list = (List) writerList.get(applicationExecutor);

        Assert.assertEquals(list.size(), EXPECTED_LIST_WRITERS_NUMBER);

    }

}
