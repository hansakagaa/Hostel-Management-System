package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
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
    @Column(name = "room_type-id")
    private String rId;
    private String type;
    @Column(name = "key_money")
    private String keyMoney;
    @Column(name = "qty")
    private int roomQty;

}
