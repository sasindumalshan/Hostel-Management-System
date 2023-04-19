package lk.ijse.hostel.service.impl;

import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.projection.UserIdProjection;
import lk.ijse.hostel.repository.UserRepository;
import lk.ijse.hostel.repository.impl.UserRepositoryImpl;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl service;
    private final UserRepository userRepository;
    private Session session;

    private UserServiceImpl() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        return service = service == null ? new UserServiceImpl() : service;
    }

    @Override
    public boolean checkUserDetails(UserDto userDto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            boolean isExists = userRepository.isExsitUser(userDto.toEntity());
            transaction.commit();
            return isExists;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean add(UserDto userDto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            String save = userRepository.save(userDto.toEntity());
            transaction.commit();
            return save==null?false:true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean checkPassword(UserDto userDto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            boolean isExists = userRepository.isPassword(userDto.toEntity());
            transaction.commit();
            return isExists;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean tableIsEmplty() {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            boolean isExists = userRepository.isTableEmpty();
            transaction.commit();
            return isExists;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<UserIdProjection> getAllIdsByOrder() {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            List<UserIdProjection> list = userRepository.getAllIDsByOrders();
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
    public UserDto get(String id) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {

            userRepository.setSession(session);
            User user = userRepository.get(id);
            transaction.commit();
            UserDto userDto=new UserDto();
            userDto.setUser_id(user.getUser_id());
            userDto.setUser_name(user.getUser_name());
            userDto.setPassword(user.getPassword());

            return userDto;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean remove(UserDto userDto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {

            userRepository.setSession(session);
            userRepository.delete(userDto.toEntity());
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
    public boolean update(UserDto userDto) {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {

            userRepository.setSession(session);
            userRepository.update(userDto.toEntity());
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
