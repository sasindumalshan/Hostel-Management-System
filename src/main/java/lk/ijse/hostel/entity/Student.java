package lk.ijse.hostel.entity;

import lk.ijse.hostel.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Student {
    @Id
    @Column(name = "student_id",columnDefinition = "VARCHAR(45)")
    private String student_id;
    private String fist_name;
    private String last_name;
    private String city;
    private String lane;
    private String street;
    private String contact_no;
    @Column(name = "birthDay",columnDefinition = "DATE")
    private LocalDate birthday;
    private String gender;



}
