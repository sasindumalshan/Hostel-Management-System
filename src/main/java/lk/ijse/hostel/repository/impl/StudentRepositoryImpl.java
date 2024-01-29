package lk.ijse.hostel.repository.impl;

import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private static StudentRepository studentRepository;
    private Session session;

    private StudentRepositoryImpl() {
    }

    public static StudentRepository getInstants() {
        return studentRepository = studentRepository == null ? new StudentRepositoryImpl() : studentRepository;
    }

    @Override
    public String save(Student Object) {
        return (String) session.save(Object);
    }

    @Override
    public void update(Student Object) {
        session.update(Object);
    }

    @Override
    public Student get(String s) {
        return session.get(Student.class, s);
    }

    @Override
    public void delete(Student Objec) {
        session.remove(Objec);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<StudentIdProjection> getAllIDsByOrders() {
        Query query = session.createQuery("select new  lk.ijse.hostel.projection.StudentIdProjection(S.id) from Student as S order by LENGTH(student_id),student_id ");
        return query.list();
    }

    @Override
    public String getAllStudentCount() {
        Query query = session.createQuery("select COUNT (S.student_id) from  Student as S");
        Object o = query.uniqueResult();
        return o.toString();
    }

    @Override
    public List<StudentIdProjection> getSearchIds(String id) {
        Query query = session.createQuery("select new lk.ijse.hostel.projection.StudentIdProjection(S.student_id) from Student as S where S.student_id like :id or S.fist_name like :f or S.last_name like :l");
        query.setParameter("id",id+"%");
        query.setParameter("f",id+"%");
        query.setParameter("l",id+"%");
        return query.list();
    }
}
