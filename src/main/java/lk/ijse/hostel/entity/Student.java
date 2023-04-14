package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Student {
    @OneToMany(mappedBy = "student",targetEntity = Reservation.class,cascade = CascadeType.ALL)
    private final List<Reservation> reservations = new ArrayList<>();
    @Id
    @Column(name = "student_id", columnDefinition = "VARCHAR(45)")
    private String student_id;
    private String fist_name;
    private String last_name;
    private String city;
    private String lane;
    private String street;
    private String contact_no;
    @Column(name = "birthDay", columnDefinition = "DATE")
    private LocalDate birthday;
    private String gender;

    @Override
    public String toString() {
        return "Student{" +
                "reservations=" + reservations +
                ", student_id='" + student_id + '\'' +
                ", fist_name='" + fist_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", city='" + city + '\'' +
                ", lane='" + lane + '\'' +
                ", street='" + street + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }
}
