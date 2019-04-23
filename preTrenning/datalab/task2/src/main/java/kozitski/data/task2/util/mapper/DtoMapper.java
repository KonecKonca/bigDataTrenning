package kozitski.data.task2.util.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface DtoMapper <DTO, REAL_TYPE>{

    REAL_TYPE map(DTO dtoType);
    default Set<REAL_TYPE> mapToSet(Collection<DTO> dtoTypes){
        Set<REAL_TYPE> result = new HashSet<>();

        dtoTypes.forEach(e -> result.add(map(e)));

        return result;
    }

}
