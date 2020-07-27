package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class DeleteCourseAndReviews {
    public static void main(String[] args) throws ParseException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Deleting Course object");

            session.beginTransaction();

            int theId = 12;
            Course courseToDelete = session.get(Course.class,theId);

            if (courseToDelete != null){
                session.delete(courseToDelete);
            }else System.out.println("*No such ID*");

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
