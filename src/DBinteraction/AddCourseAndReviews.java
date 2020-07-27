package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddCourseAndReviews {
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
            session.beginTransaction();

            Course tempCourse = new Course("IceCreamEating from A to Z");

            tempCourse.addReview(new Review("Yummy"));
            tempCourse.addReview(new Review("I'm getting better at this"));
            tempCourse.addReview(new Review("Sweet"));

            System.out.println("saving reviews...");
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
