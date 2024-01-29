package lk.ijse.hostel.repository;

import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.projection.StudentIdProjection;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student,String>{
    List<StudentIdProjection> getAllIDsByOrders();

    String getAllStudentCount();

    List<StudentIdProjection> getSearchIds(String id);
}
