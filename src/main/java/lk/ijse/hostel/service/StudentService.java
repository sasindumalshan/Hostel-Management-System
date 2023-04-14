package lk.ijse.hostel.service;

import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.projection.StudentIdProjection;

import java.util.ArrayList;
import java.util.List;

public interface StudentService {
    String saveStudent(StudentDto studentDto);

    List<StudentIdProjection> getAllIdsByOrder();

    StudentDto get(String student_id);

    boolean updateStudent(StudentDto studentDto);

    boolean remove(StudentDto dto);
}
