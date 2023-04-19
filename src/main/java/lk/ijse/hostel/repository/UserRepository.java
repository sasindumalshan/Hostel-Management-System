package lk.ijse.hostel.repository;

import lk.ijse.hostel.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
    boolean isExsitUser(User user);

    boolean isPassword(User toEntity);

    boolean isTableEmpty();
}
