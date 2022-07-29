package lk.ijse.Hibernate.dto;

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
public class User_passwordDTO {
    private String userName;
    private String password;
    private String nic;
    private String name;
    private String contact;
    private String address;

}
