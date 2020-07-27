package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddCourse {
    public static void main(String[] args) throws ParseException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int theId = 1;

            session.beginTransaction();

            Instructor tempInstructor = session.get(Instructor.class,theId);

            Course tempCourse1 = new Course("Air Guitar");
//            Course tempCourse2 = new Course("Sushi Sticks Master");

            tempInstructor.addCourse(tempCourse1);
//            tempInstructor.addCourse(tempCourse2);


            System.out.println("Save courses...");
            session.save(tempCourse1);
//            session.save(tempCourse2);

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
