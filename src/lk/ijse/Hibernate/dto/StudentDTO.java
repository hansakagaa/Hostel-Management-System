package lk.ijse.Hibernate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
/**
    @author : Hasii-boy
**/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StudentDTO {
    private String sId;
    private String name;
    private String address;
    private String contact;
    private LocalDate dateOfBirth;
    private String gender;
}
