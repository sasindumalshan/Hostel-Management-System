package lk.ijse.hostel.repository.impl;

import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.RoomCountProjection;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.repository.RoomRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class RoomRepositoryImpl implements RoomRepository {

    private static RoomRepositoryImpl roomRepository;
    private Session session;

    private RoomRepositoryImpl() {
    }

    public static RoomRepository getInstance() {
        roomRepository = roomRepository == null
                ? new RoomRepositoryImpl()
                : roomRepository;
        return roomRepository;
    }

    @Override
    public String save(Room room) {
        return (String) session.save(room);
    }

    @Override
    public void update(Room Object) {
        session.update(Object);
    }

    @Override
    public Room get(String s) {
        return session.get(Room.class, s);
    }

    @Override
    public void delete(Room Object) {
        session.delete(Object);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<RoomIdProjection> getAllIds() {
        String sql = "SELECT new lk.ijse.hostel.projection.RoomIdProjection(R.id) FROM Room AS R";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public RoomDto getDataSearchForId(String roomId) {
        String sql = "SELECT  Room  FROM Room ";
        Query query = session.createQuery(sql);
        List<RoomDto> list = query.list();
        session.close();
        if (!list.isEmpty()) {
            return new RoomDto(
                    list.get(1).getRoomTypeId(),
                    list.get(1).getKeyMoney(),
                    String.valueOf(list.get(1).getQty()),
                    Integer.valueOf(list.get(1).getType())
            );
        } else {
            return null;
        }

    }

    @Override
    public RoomCountProjection getAllRoomCount() {
        Query query = session.createQuery("select count (roomTypeId) from  Room ");
        Object o = query.uniqueResult();
        System.out.println(o.toString());
        RoomCountProjection projection = new RoomCountProjection(o.toString());
        return projection;


    }

    @Override
    public List<RoomIdProjection> getDataSearch(String id) {
        Query query = session.createQuery("select new lk.ijse.hostel.projection.RoomIdProjection (R.roomTypeId) from Room as R where R.roomTypeId like :id or R.type like :t");
        query.setParameter("id",id+"%");
        query.setParameter("t",id+"%");
        List list = query.list();
        return list;

    }

    @Override
    public List<RoomIdProjection> getIdForOrder() {
        Query query = session.createQuery("select new lk.ijse.hostel.projection.RoomIdProjection (R.roomTypeId ) from Room as R order by LENGTH(R.roomTypeId),R.roomTypeId ");
        List list = query.list();
        return list;
    }
}
