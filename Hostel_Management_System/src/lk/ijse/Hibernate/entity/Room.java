package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
    @author : Hasii-boy
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Room implements SuperEntity{
    @Id
    private String rId;
    private String type;
    private String keyMoney;
    private int roomQty;

}
