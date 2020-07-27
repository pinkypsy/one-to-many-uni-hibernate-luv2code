package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddDemo {
    public static void main(String[] args) throws ParseException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating new Instructor object");
           /* Instructor instructor =
                    new Instructor("Chad", "Derby", "chad@luv2code.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("youtube.com/luv", "coding");*/
            Instructor instructor =
                    new Instructor("Mango", "Jerry", "darby@luv2code.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("youtube.com/luv2code", "code");

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();
            session.save(instructor);
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
