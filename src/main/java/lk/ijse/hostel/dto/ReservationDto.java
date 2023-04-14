package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ReservationDto {
    private String res_id;
    private LocalDate date;
    private String status;
    private Student student;
    private Room room;

    public Reservation toEntity() {
      Reservation reservation=  new Reservation();
      reservation.setRes_id(this.res_id);
      reservation.setDate(this.date);
      reservation.setStatus(this.status);
      reservation.setStudent(this.student);
      reservation.setRoom(this.room);
      return reservation;
    }
}
