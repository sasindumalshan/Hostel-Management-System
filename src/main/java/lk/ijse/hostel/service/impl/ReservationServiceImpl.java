package lk.ijse.hostel.service.impl;

import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.projection.ReservationIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.repository.ReservationRepository;
import lk.ijse.hostel.repository.impl.ReservationRepositoryImpl;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private Session session;
    private static ReservationService service;
    private ReservationDto reservationDto;
    private ReservationRepository reservationRepository;
    private ReservationServiceImpl(){
        reservationRepository= ReservationRepositoryImpl.getInstance();
    }
    public static ReservationService getInstance(){
        return service=service==null?new ReservationServiceImpl():service;
    }


    @Override
    public List<ReservationIdProjection> getAllIdsByOrder() {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            List<ReservationIdProjection> list = reservationRepository.getAllIDsByOrders();
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
    public boolean save(ReservationDto dto) {
        session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            String save = reservationRepository.save(dto.toEntity());
            transaction.commit();
            return save==null?false:true;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public ReservationDto get(String res_id) {
        session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            Reservation reservation = reservationRepository.get(res_id);
            reservationDto=reservation.toEntity();
            transaction.commit();
            return reservationDto;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
