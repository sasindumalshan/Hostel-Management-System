package lk.ijse.hostel.service;

import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.RoomIdProjection;

import java.util.List;

public interface RoomService {
    String saveRoom(RoomDto roomDto);
    Room get(String id);

    boolean update(RoomDto roomDto);

    List<RoomIdProjection> getAllIds();

    RoomDto getDataSearchForId(String roomId);
}
