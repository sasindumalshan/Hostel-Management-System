package lk.ijse.hostel.service;

import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.projection.ReservationIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;

import java.util.List;

public interface ReservationService {
    List<ReservationIdProjection> getAllIdsByOrder();

    boolean save(ReservationDto dto);

    ReservationDto get(String res_id);

    boolean remove(ReservationDto reservationDto);
}
