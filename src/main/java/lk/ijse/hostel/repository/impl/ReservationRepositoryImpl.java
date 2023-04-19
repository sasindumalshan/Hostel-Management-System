package lk.ijse.hostel.repository.impl;

import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.projection.ReservationIdProjection;
import lk.ijse.hostel.repository.ReservationRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {
    private static ReservationRepositoryImpl repository;
    private Session session;

    private ReservationRepositoryImpl() {
    }

    public static ReservationRepositoryImpl getInstance() {
        return repository = repository == null ? new ReservationRepositoryImpl() : repository;
    }

    @Override
    public String save(Reservation Object) {
        System.out.println(session);
        return (String) session.save(Object);
    }

    @Override
    public void update(Reservation Object) {
        session.update(Object);
    }

    @Override
    public Reservation get(String s) {
        return session.get(Reservation.class, s);
    }

    @Override
    public void delete(Reservation Objec) {
        session.remove(Objec);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<ReservationIdProjection> getAllIDsByOrders() {
        Query query = session.createQuery("select new  lk.ijse.hostel.projection.ReservationIdProjection(R.id) from Reservation as R order by LENGTH(res_id),res_id ");
        return query.list();
    }
}
