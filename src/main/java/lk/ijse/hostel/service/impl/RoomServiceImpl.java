package lk.ijse.hostel.service.impl;

import javafx.scene.control.Alert;
import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.repository.RoomRepository;
import lk.ijse.hostel.repository.impl.RoomRepositoryImpl;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static RoomService roomService;
    private final RoomRepository roomRepository;
    private Session session;

    private RoomServiceImpl() {
        roomRepository = RoomRepositoryImpl.getInstance();
    }

    public static RoomService getInstance() {
        return null == roomService
                ? roomService = new RoomServiceImpl()
                : roomService;
    }

    @Override
    public String saveRoom(RoomDto roomDto) {
        roomService = RoomServiceImpl.getInstance();

        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            System.out.println(roomRepository);
            roomRepository.setSession(session);
            String roomId = roomRepository.save(roomDto.toEntity());
            transaction.commit();
            session.close();
            return roomId;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public Room get(String id) {
        roomService = RoomServiceImpl.getInstance();
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            Room room = roomRepository.get(id);
            transaction.commit();
            session.close();
            return room;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(RoomDto roomDto) {
        roomService = RoomServiceImpl.getInstance();
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            roomRepository.update(roomDto.toEntity());
            transaction.commit();
            session.close();
           return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RoomIdProjection> getAllIds() {

       List<RoomIdProjection> list=new ArrayList<>();
        roomService = RoomServiceImpl.getInstance();
        session = FactoryConfiguration.getInstance().getSession();
        roomRepository.setSession(session);
        System.out.println(session);
        list = roomRepository.getAllIds();
        System.out.println(list);
        //    Transaction transaction = session.beginTransaction();
/*
        try {
            roomRepository.setSession(session);
           list= roomRepository.getAllIds();
            transaction.commit();
            return list;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            new Alert(Alert.AlertType.CONFIRMATION, "Something wrong ...!").show();
*/

            return list;
        }


    @Override
    public RoomDto getDataSearchForId(String roomId) {
        roomService = RoomServiceImpl.getInstance();
        session = FactoryConfiguration.getInstance().getSession();
     /*   Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            RoomDto roomDto = roomRepository.getDataSearchForId(roomId);
            transaction.commit();
            return roomDto;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();

            return null;
        }*/
        RoomDto roomDto = roomRepository.getDataSearchForId(roomId);
        return roomDto;
    }
}
