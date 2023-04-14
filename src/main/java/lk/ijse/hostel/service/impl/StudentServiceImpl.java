package lk.ijse.hostel.service.impl;

import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.repository.StudentRepository;
import lk.ijse.hostel.repository.impl.StudentRepositoryImpl;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static StudentService service;
    private final StudentRepository studentRepository;
    Session session;
    StudentDto studentDto;
    Student student;

    private StudentServiceImpl() {
        studentRepository = StudentRepositoryImpl.getInstants();

    }

    public static StudentService getInstants() {
        return service = service == null ?
                new StudentServiceImpl()
                : service;
    }


    @Override
    public String saveStudent(StudentDto studentDto) {

        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            String save = studentRepository.save(studentDto.toEntity());
            transaction.commit();
            return save;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public List<StudentIdProjection> getAllIdsByOrder() {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            List<StudentIdProjection> list = studentRepository.getAllIDsByOrders();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public StudentDto get(String student_id) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            student = studentRepository.get(student_id);
            transaction.commit();
            studentDto = new StudentDto();
            studentDto.setStudent_id(student.getStudent_id());
            studentDto.setFist_name(student.getFist_name());
            studentDto.setLast_name(student.getLast_name());
            studentDto.setCity(student.getCity());
            studentDto.setStreet(student.getStreet());
            studentDto.setLane(student.getLane());
            studentDto.setContact_no(student.getContact_no());
            studentDto.setBirthday(student.getBirthday());
            studentDto.setGender(student.getGender());
            System.out.println(studentDto);
            return studentDto;
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.update(studentDto.toEntity());
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean remove(StudentDto dto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.delete(studentDto.toEntity());
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }


}
