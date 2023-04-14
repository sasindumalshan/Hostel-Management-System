package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Room {
    @OneToMany(mappedBy = "room" ,cascade = CascadeType.ALL ,targetEntity = Reservation.class)
    private final List<Reservation> reservations = new ArrayList<>();
    @Id
    @Column(name = "room_id", columnDefinition = "VARCHAR(64)")
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;


}
