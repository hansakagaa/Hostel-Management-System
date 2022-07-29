package lk.ijse.Hibernate.util;

import lk.ijse.Hibernate.entity.Reservation;
import lk.ijse.Hibernate.entity.Room;
import lk.ijse.Hibernate.entity.Student;
import lk.ijse.Hibernate.entity.User_password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
    @author : Hasii-boy
**/

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure("/lk/ijse/Hibernate/resources/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User_password.class);
        sessionFactory = configuration.buildSessionFactory();
//
//        File file = new File("lk/ijse/Hibernate/resources/hibernate.properties");
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties(file).build();
//        sessionFactory = new MetadataSources(registry)
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Room.class)
//                .addAnnotatedClass(Reservation.class)
//                .addAnnotatedClass(User_password.class)
//                .buildMetadata().buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
