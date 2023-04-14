package lk.ijse.hostel.repository;

public interface CrudRepository<T,ID> extends SuperRepository {

    ID save(T Object);

    void update(T Object);

    T get (ID id);

    void delete(T Objec);


}
