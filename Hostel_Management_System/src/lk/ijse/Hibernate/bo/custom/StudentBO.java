package lk.ijse.Hibernate.bo.custom;


import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.StudentDTO;

/**
    @author : Hasii-boy
**/

public interface StudentBO extends SuperBO {

    boolean exitsStudent(String id) throws Exception;

    boolean saveStudent(StudentDTO dto)throws Exception;

}
