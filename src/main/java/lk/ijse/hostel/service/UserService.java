package lk.ijse.hostel.service;

import lk.ijse.hostel.dto.UserDto;

public interface UserService {
    boolean checkUserDetails(UserDto userDto);

    boolean add(UserDto userDto);

    boolean checkPassword(UserDto userDto);

    boolean tableIsEmplty();
}
