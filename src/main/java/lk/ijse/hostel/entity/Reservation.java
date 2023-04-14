package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity

public class Reservation {
    @Id
    @Column(name = "res_id", columnDefinition = "VARCHAR(45)")
    private String res_id;
    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;
    private String status;
    @ManyToOne(targetEntity = Student.class,cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(targetEntity = Room.class,cascade = CascadeType.ALL)
    private Room room;

    @Override
    public String toString() {
        return "Reservation{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
}

