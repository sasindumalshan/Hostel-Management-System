package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class StudentDto {
    private String student_id;
    private String fist_name;
    private String last_name;
    private String city;
    private String lane;
    private String street;
    private String contact_no;
    private LocalDate birthday;
    private String gender;

    public Student toEntity() {
        Student student=new Student();
        student.setStudent_id(this.getStudent_id());
        student.setFist_name(this.fist_name);
        student.setLast_name(this.last_name);
        student.setCity(this.city);
        student.setLane(this.lane);
        student.setStreet(this.street);
        student.setContact_no(this.contact_no);
        student.setBirthday(this.birthday);
        student.setGender(this.gender);
        return student;
    }
}
