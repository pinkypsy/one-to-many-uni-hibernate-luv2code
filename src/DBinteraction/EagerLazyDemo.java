package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            System.out.println("Start transaction");

            int theId = 1;

            Instructor theInstructor = session.get(Instructor.class,theId);

            if (theInstructor != null) {
                System.out.println("Instructor: " + theInstructor);
                session.close();
                System.out.println("Courses: " + theInstructor.getCourses());

            }else System.out.println("No such ID");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
