package kozitski.data.task2.dao;

import java.util.Collection;

public interface CommonDao<T> {

    void insertAll(Collection<? extends T> elems);
    void clearDb();

}
