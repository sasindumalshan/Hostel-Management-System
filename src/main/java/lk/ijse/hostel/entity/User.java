package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class User {
    @Id
    @Column(name = "user_id",columnDefinition = "VARCHAR(64)")
    private String user_id;
    private String user_name;
    private String password;

}
