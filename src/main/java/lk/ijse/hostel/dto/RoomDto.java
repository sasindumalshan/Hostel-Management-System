package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Room;

public class RoomDto {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int Qty;


    public RoomDto() {
    }

    public RoomDto(String roomTypeId, String type, String keyMoney, int qty) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.keyMoney = keyMoney;
        Qty = qty;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", Qty=" + Qty +
                '}';
    }

    public Room toEntity() {
        Room room=new Room();
        room.setRoomTypeId(this.roomTypeId);
        room.setType(this.type);
        room.setKeyMoney(this.keyMoney);
        room.setQty(this.Qty);
        return room;
    }
}
