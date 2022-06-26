package lk.ijse.Hibernate.dto;

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
public class ReservationDTO {
    private String resId;
    private LocalDate date;
    private String sId;
    private String rId;
    private String status;

}
