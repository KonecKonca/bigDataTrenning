package kozitski.data.task2.service.stop;

import kozitski.data.task2.domain.Stop;

import java.util.Collection;
import java.util.Set;

public interface StopsService {

    Set<Stop> getAllStopsByDate(String latitude, String longitude, String date);
    Set<Stop> getAllStops(String latitude, String longitude);
    void clearDb();
    void updateDb(Collection<? extends Stop> crimes);

}
