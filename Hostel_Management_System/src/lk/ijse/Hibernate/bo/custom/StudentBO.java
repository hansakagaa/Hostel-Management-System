package lk.ijse.Hibernate.bo.custom;


import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.StudentDTO;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/

public interface StudentBO extends SuperBO {

    boolean exitsStudent(String id) throws Exception;

    boolean saveStudent(StudentDTO dto)throws Exception;

    boolean updateStudent(StudentDTO dto)throws Exception;

    boolean deleteStudent(String id)throws Exception;

    ArrayList<StudentDTO> getAllStudent() throws Exception;

}
