package lk.ijse.hostel.service.impl;

import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.repository.UserRepository;
import lk.ijse.hostel.repository.impl.UserRepositoryImpl;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
