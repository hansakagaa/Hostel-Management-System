package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
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
    @Column(name = "student_id")
    private String sId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(name = "contact_no")
    private String contact;
    @Column(name = "dob", columnDefinition = "DATE")
    private LocalDate dateOfBirth;
    private String gender;

}

