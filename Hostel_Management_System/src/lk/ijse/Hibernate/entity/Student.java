package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

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
    private LocalDate dateOfBirth;
    private String gender;

}

