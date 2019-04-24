package kozitski.data.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * The type Destination dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DestinationDTO {
    private Optional<Integer> srchDestinationId;
    private List<Optional<Double>> d;
}
