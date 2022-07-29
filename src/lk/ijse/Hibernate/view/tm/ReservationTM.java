package lk.ijse.Hibernate.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
    @author : Hasii-boy
**/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReservationTM {
    private String resId;
    private LocalDate date;
    private String sId;
    private String rId;
    private String status;

}
