package kozitski.data.converter.io.writer;

import kozitski.data.converter.dto.TestDTO;
import kozitski.data.converter.io.IOConstant;
import kozitski.data.converter.io.reader.TestCsvReader;
import kozitski.data.converter.scheme.TestSchemaGenerator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class TestAvroWriter {

    @Value("${data.schema.test.id}")
    private String id;
    @Value("${data.schema.test.dateTime}")
    private String dateTime;
    @Value("${data.schema.test.siteName}")
    private String siteName;
    @Value("${data.schema.test.posaContinent}")
    private String posaContinent;
    @Value("${data.schema.test.userLocationCountry}")
    private String userLocationCountry;
    @Value("${data.schema.test.userLocationRegion}")
    private String userLocationRegion;
    @Value("${data.schema.test.userLocationCity}")
    private String userLocationCity;
    @Value("${data.schema.test.origDestinationDistance}")
    private String origDestinationDistance;
    @Value("${data.schema.test.userId}")
    private String userId;
    @Value("${data.schema.test.isMobile}")
    private String isMobile;
    @Value("${data.schema.test.isPackage}")
    private String isPackage;
    @Value("${data.schema.test.channel}")
    private String channel;
    @Value("${data.schema.test.srchCi}")
    private String srchCi;
    @Value("${data.schema.test.srchCo}")
    private String srchCo;
    @Value("${data.schema.test.srchAdultsCnt}")
    private String srchAdultsCnt;
    @Value("${data.schema.test.srchChildrenCnt}")
    private String srchChildrenCnt;
    @Value("${data.schema.test.srchRmCnt}")
    private String srchRmCnt;
    @Value("${data.schema.test.srchDestinationId}")
    private String srchDestinationId;
    @Value("${data.schema.test.srchDestinationTypeId}")
    private String srchDestinationTypeId;
    @Value("${data.schema.test.hotelContinent}")
    private String hotelContinent;
    @Value("${data.schema.test.hotelCountry}")
    private String hotelCountry;
    @Value("${data.schema.test.hotelMarket}")
    private String hotelMarket;

    @Setter private String avroFilePath = IOConstant.TEST_WRITE_PATH;
    private TestSchemaGenerator schemaGenerator;
    private TestCsvReader csvReader;

    @Autowired
    public void setSchemaGenerator(TestSchemaGenerator schemaGenerator) {
        this.schemaGenerator = schemaGenerator;
    }

    @Autowired
    public void setCsvReader(TestCsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public void writeToAvro(){
        Schema schema = schemaGenerator.generateSchema();
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);

        Configuration conf = new Configuration();
        try (
                FileSystem fs = FileSystem.get(URI.create(avroFilePath), conf);
                OutputStream out = fs.create(new Path(avroFilePath))
        ){

            while (csvReader.isHasMore()){
                List<TestDTO> testDTOS = csvReader.readPart();

                List<GenericRecord> tests = new LinkedList<>();
                testDTOS.forEach(e -> {
                    if(e != null){
                        GenericData.Record record = new GenericData.Record(schema);

                        record.put(id, e.getId().orElse(null));
                        record.put(dateTime, e.getDateTime().orElse(null));
                        record.put(siteName, e.getSiteName().orElse(null));
                        record.put(posaContinent, e.getPosaContinent().orElse(null));
                        record.put(userLocationCountry, e.getUserLocationCountry().orElse(null));
                        record.put(userLocationRegion, e.getUserLocationRegion().orElse(null));
                        record.put(userLocationCity, e.getUserLocationCity().orElse(null));
                        record.put(origDestinationDistance, e.getOrigDestinationDistance().orElse(null));
                        record.put(userId, e.getUserId().orElse(null));
                        record.put(isMobile, e.getIsMobile().orElse(null));
                        record.put(isPackage, e.getIsPackage().orElse(null));
                        record.put(channel, e.getChannel().orElse(null));
                        record.put(srchCi, e.getSrchCi().orElse(null));
                        record.put(srchCo, e.getSrchCo().orElse(null));
                        record.put(srchAdultsCnt, e.getSrchAdultsCnt().orElse(null));
                        record.put(srchChildrenCnt, e.getSrchChildrenCnt().orElse(null));
                        record.put(srchRmCnt, e.getSrchRmCnt().orElse(null));
                        record.put(srchDestinationId, e.getSrchDestinationId().orElse(null));
                        record.put(srchDestinationTypeId, e.getSrchDestinationTypeId().orElse(null));
                        record.put(hotelContinent, e.getHotelContinent().orElse(null));
                        record.put(hotelCountry, e.getHotelCountry().orElse(null));
                        record.put(hotelMarket, e.getHotelMarket().orElse(null));

                        tests.add(record);
                    }
                });

                try(DataFileWriter dataFileWriter = new DataFileWriter<>(datumWriter)) {
                    dataFileWriter.create(schema, out);

                    for (GenericRecord record: tests) {
                        dataFileWriter.append(record);
                        log.info("....record was appended");
                    }
                }

            }

        }
        catch (IOException e) {
            log.error("Exception during AVRO-file creating", e);
        }

    }

}
