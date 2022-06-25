package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

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
    private String resId;
    private Date date;
    private String sId;
    private String rId;
    private String status;

}
