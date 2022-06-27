package lk.ijse.Hibernate.bo.custom.impl;


import lk.ijse.Hibernate.bo.custom.StudentBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.StudentDAO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.entity.Student;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean exitsStudent(String id) throws Exception {
        return studentDAO.exits(id);
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getSId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getDateOfBirth(),dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getSId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getDateOfBirth(),dto.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
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
}
