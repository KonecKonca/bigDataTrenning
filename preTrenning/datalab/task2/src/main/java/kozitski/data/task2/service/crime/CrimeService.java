package kozitski.data.task2.service.crime;

import kozitski.data.task2.domain.Crime;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CrimeService {

    Set<Crime> getAllCrimesByDate(String latitude, String longitude, String date);
    Set<Crime> getAllCrimes(String latitude, String longitude);
    void clearDb();
    void updateDb(Collection<? extends Crime> crimes);

}
