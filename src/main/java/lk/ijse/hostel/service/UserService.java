package lk.ijse.hostel.service;

import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.projection.UserIdProjection;

import java.util.List;

public interface UserService {
    boolean checkUserDetails(UserDto userDto);

    boolean add(UserDto userDto);

    boolean checkPassword(UserDto userDto);

    boolean tableIsEmplty();

    List<UserIdProjection> getAllIdsByOrder();
}
