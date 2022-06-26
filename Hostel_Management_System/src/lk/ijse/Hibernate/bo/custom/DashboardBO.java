package lk.ijse.Hibernate.bo.custom;

import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.ReservationDTO;
import lk.ijse.Hibernate.dto.RoomDTO;
import lk.ijse.Hibernate.dto.StudentDTO;

import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public interface DashboardBO extends SuperBO {

    boolean exitsStudent(String id) throws Exception;

    boolean exitsRoom(String id) throws Exception;

    StudentDTO findStudent(String id) throws Exception;

    RoomDTO findRoom(String id) throws Exception;

    ArrayList<StudentDTO> getAllStudent() throws Exception;

    ArrayList<RoomDTO> getAllRoom() throws Exception;

    boolean reserveStudent(ReservationDTO dto)throws Exception;
}
