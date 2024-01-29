package lk.ijse.hostel.repository.impl;

import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.projection.UserIdProjection;
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

        return (String) session.save(Object);
    }

    @Override
    public void update(User Object) {

    }

    @Override
    public User get(String s) {
       return session.get(User.class,s);
    }

    @Override
    public void delete(User Objec) {
        session.delete(Objec);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean isExsitUser(User user) {
        Query query = session.createQuery("from User as U where U.user_name=:un and U.password=:pd");
        query.setParameter("un", user.getUser_name());
        query.setParameter("pd", user.getPassword());
        User user1 = (User) query.uniqueResult();

        System.out.println(user1);
        return user1 != null;
    }

    @Override
    public boolean isPassword(User user) {
        Query query = session.createQuery("from User as U where U.user_id=:un and U.password=:pd");
        query.setParameter("un", user.getUser_id());
        query.setParameter("pd", user.getPassword());
        User user1 = (User) query.uniqueResult();

        System.out.println(user1);
        return user1 != null;
    }

    @Override
    public boolean isTableEmpty() {
        Query query = session.createQuery("from User as U ");
        List list = query.list();
        System.out.println(list.size());
        return list.size() != 0;
    }

    @Override
    public List<UserIdProjection> getAllIDsByOrders() {
        Query query = session.createQuery("select new  lk.ijse.hostel.projection.UserIdProjection(S.id) from User as S order by LENGTH(user_id),user_id ");
        return query.list();
    }
}
