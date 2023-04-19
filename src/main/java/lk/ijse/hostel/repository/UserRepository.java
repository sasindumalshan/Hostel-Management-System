package lk.ijse.hostel.repository;

import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.projection.UserIdProjection;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    boolean isExsitUser(User user);

    boolean isPassword(User toEntity);

    boolean isTableEmpty();

    List<UserIdProjection> getAllIDsByOrders();
}
