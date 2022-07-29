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
public class User_password implements SuperEntity{
    @Id
    private String userName;
    @Id
    private String password;
    @Id
    private String nic;
    private String name;
    private String contact;
    private String address;

}
