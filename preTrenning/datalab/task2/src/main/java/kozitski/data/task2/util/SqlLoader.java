package kozitski.data.task2.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SqlLoader {

    public String returnSql(Resource resource){
        StringBuilder sqlBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));){
            List<String> strings = reader.lines().collect(Collectors.toList());
            strings.forEach(s -> {
                sqlBuilder.append(s);
                sqlBuilder.append(StringUtils.SPACE);
            });
        }
        catch (FileNotFoundException e) {
            log.error("Sql file wasn't founded", e);
        }
        catch (IOException e) {
            log.error("Sql file is unreadable", e);
        }

        return sqlBuilder.toString();
    }

}
