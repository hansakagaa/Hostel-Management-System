package lk.ijse.Hibernate.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
    @author : Hasii-boy
**/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RoomTM {
    private String rId;
    private String type;
    private String keyMoney;
    private int roomQty;

}
