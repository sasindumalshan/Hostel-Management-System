package lk.ijse.hostel.repository;

import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.projection.ReservationIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,String>{
    List<ReservationIdProjection> getAllIDsByOrders();
}
