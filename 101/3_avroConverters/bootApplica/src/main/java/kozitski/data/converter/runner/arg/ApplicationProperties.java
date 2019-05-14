package kozitski.data.converter.runner.arg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationProperties {

    @Value("${application.input.path}")
    private String inputPath;
    @Value("${application.output.path}")
    private String outputPath;
    @Value("${application.host.name}")
    private String hostName;
    @Value("${application.port.value}")
    private String portValue;
    @Value("${application.batch.size}")
    private int batchSize;

}
