package DBinteraction;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class DeleteDemo {
    public static void main(String[] args) throws ParseException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Deleting Instructor object");

            session.beginTransaction();

            int theId = 11;
            Instructor instructorToDelete = session.get(Instructor.class,theId);

            if (instructorToDelete != null){
                session.delete(instructorToDelete);
            }else System.out.println("*No such ID*");


            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
