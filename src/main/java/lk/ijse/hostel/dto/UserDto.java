package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {
    private String user_id;
    private String user_name;
    private String password;

    public User toEntity() {
        User user=new User();
        user.setUser_id(this.user_id);
        user.setUser_name(this.user_name);
        user.setPassword(this.password);
        return user;
    }
}
