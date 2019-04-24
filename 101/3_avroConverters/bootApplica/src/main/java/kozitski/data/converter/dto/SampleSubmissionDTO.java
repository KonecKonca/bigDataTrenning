package kozitski.data.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * The type Sample submission dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleSubmissionDTO {
    private Optional<Integer> id;
    private Optional<String> hotelCluster;
}
