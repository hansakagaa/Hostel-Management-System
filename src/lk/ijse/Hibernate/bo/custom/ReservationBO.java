package lk.ijse.Hibernate.bo.custom;

import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.ReservationDTO;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public interface ReservationBO extends SuperBO {

    boolean exitsRoomTypeId(String id)throws Exception;

    boolean exitsReservation(String id) throws Exception;

    boolean saveReservation(ReservationDTO dto)throws Exception;

    boolean updateReservation(ReservationDTO dto)throws Exception;

    boolean deleteReservation(String id)throws Exception;

    ArrayList<ReservationDTO> getAllReservation() throws Exception;
}
