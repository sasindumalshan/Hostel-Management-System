package lk.ijse.hostel.repository.impl;

import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl repository;
    private Session session;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        return repository = repository == null ? new UserRepositoryImpl() : repository;
    }

    @Override
    public String save(User Object) {
        return null;
    }

    @Override
    public void update(User Object) {

    }

    @Override
    public User get(String s) {
        return null;
    }

    @Override
    public void delete(User Objec) {

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean isExsitUser(User user) {
        Query query = session.createQuery("from User as U where U.user_name=:un and U.password=:pd");
        query.setParameter("un",user.getUser_name());
        query.setParameter("pd",user.getPassword());
        User user1 = (User) query.uniqueResult();

        System.out.println(user1);
        return user1==null?false:true;
    }
}
