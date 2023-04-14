package lk.ijse.hostel.repository;

import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.RoomIdProjection;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,String> {
    List<RoomIdProjection> getAllIds();

    RoomDto getDataSearchForId(String roomId);
}
