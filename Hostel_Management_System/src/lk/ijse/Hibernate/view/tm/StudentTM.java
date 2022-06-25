package lk.ijse.Hibernate.view.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
    @author : Hasii-boy
**/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StudentTM {
    private String sId;
    private String name;
    private String address;
    private String contact;
    private Date dateOfBirth;
    private String gender;
}
