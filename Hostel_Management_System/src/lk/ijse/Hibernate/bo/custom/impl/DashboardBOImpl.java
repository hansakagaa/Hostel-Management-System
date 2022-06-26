package lk.ijse.Hibernate.bo.custom.impl;

import lk.ijse.Hibernate.bo.custom.DashboardBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.ReservationDAO;
import lk.ijse.Hibernate.dao.custom.RoomDAO;
import lk.ijse.Hibernate.dao.custom.StudentDAO;
import lk.ijse.Hibernate.dto.ReservationDTO;
import lk.ijse.Hibernate.dto.RoomDTO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.entity.Reservation;
import lk.ijse.Hibernate.entity.Room;
import lk.ijse.Hibernate.entity.Student;

import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public class DashboardBOImpl implements DashboardBO {
    private final ReservationDAO reservationDAO = DAOFactory.getInstance().getDAO(DAOType.RESERVATION);
    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    private final RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);
    @Override
    public boolean exitsStudent(String id) throws Exception{
        return studentDAO.exits(id);
    }

    @Override
    public boolean exitsRoom(String id) throws Exception {
        return roomDAO.exits(id);
    }

    @Override
    public StudentDTO findStudent(String id) throws Exception {
        Student std = studentDAO.find(id);
        return new StudentDTO(std.getSId(),std.getName(),std.getAddress(),std.getContact(),std.getDateOfBirth(),std.getGender());
    }

    @Override
    public RoomDTO findRoom(String id) throws Exception {
        Room room = roomDAO.find(id);
        return new RoomDTO(room.getRId(),room.getType(),room.getKeyMoney(),room.getRoomQty());
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        ArrayList<Student> students = studentDAO.getAll();
        ArrayList<StudentDTO> dtoS = new ArrayList<>();
        for (Student student : students) {
            dtoS.add(new StudentDTO(student.getSId(),student.getName(),student.getAddress(),student.getContact(),student.getDateOfBirth(),student.getGender()));
        }
        return dtoS;
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws Exception {
        ArrayList<Room> rooms = roomDAO.getAll();
        ArrayList<RoomDTO> dtoS = new ArrayList<>();
        for (Room room : rooms) {
            dtoS.add(new RoomDTO(room.getRId(),room.getType(),room.getKeyMoney(),room.getRoomQty()));
        }
        return dtoS;
    }

    @Override
    public boolean reserveStudent(ReservationDTO dto) throws Exception {
        return reservationDAO.save(new Reservation(dto.getResId(), dto.getDate(), dto.getSId(), dto.getRId(), dto.getStatus()));
    }
}
