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
public class Student implements SuperEntity {
    @Id
    private String sId;
    private String name;
    private String address;
    private String contact;
    private Date dateOfBirth;
    private String gender;

}

