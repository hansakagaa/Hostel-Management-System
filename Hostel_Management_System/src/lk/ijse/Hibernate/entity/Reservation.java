package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
    @author : Hasii-boy
**/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Reservation implements SuperEntity{
    @Id
    @Column(name = "res_id")
    private String resId;
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @Column(name = "student_id")
    private String sId;
    @Column(name = "room_type_id")
    private String rId;
    private String status;

}
