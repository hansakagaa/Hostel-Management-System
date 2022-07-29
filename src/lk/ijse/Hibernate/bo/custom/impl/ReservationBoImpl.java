package lk.ijse.Hibernate.bo.custom.impl;

import lk.ijse.Hibernate.bo.custom.ReservationBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.ReservationDAO;
import lk.ijse.Hibernate.dto.ReservationDTO;
import lk.ijse.Hibernate.entity.Reservation;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public class ReservationBoImpl implements ReservationBO {
    private final ReservationDAO reservationDAO = DAOFactory.getInstance().getDAO(DAOType.RESERVATION);

    @Override
    public boolean exitsRoomTypeId(String id) throws Exception {
        return reservationDAO.exitsRoomId(id);
    }

    @Override
    public boolean exitsReservation(String id) throws Exception {
        return reservationDAO.exits(id);
    }

    @Override
    public boolean saveReservation(ReservationDTO dto) throws Exception {
        return reservationDAO.save(new Reservation(dto.getResId(), dto.getDate(), dto.getSId(), dto.getRId(), dto.getStatus()));
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) throws Exception {
        return reservationDAO.update(new Reservation(dto.getResId(), dto.getDate(), dto.getSId(), dto.getRId(), dto.getStatus()));
    }

    @Override
    public boolean deleteReservation(String id) throws Exception {
        return reservationDAO.delete(id);
    }

    @Override
    public ArrayList<ReservationDTO> getAllReservation() throws Exception {
        ArrayList<Reservation> reservations = reservationDAO.getAll();
        ArrayList<ReservationDTO> dtoS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            dtoS.add(new ReservationDTO(reservation.getResId(),reservation.getDate(),reservation.getSId(),reservation.getRId(),reservation.getStatus()));
        }
        return dtoS;
    }
}
