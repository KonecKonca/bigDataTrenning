package kozitski.data.task2.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    private String latitude;
    private Street street;
    private String longitude;

    public Street getStreet() {
        if(street == null){
            return new Street();
        }
        else {
            return street;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Street {
        private long id;
        private String name;
    }
}
