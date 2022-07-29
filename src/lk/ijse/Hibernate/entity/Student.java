package lk.ijse.Hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Reservation> reservations;
}

