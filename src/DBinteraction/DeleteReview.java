package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class DeleteReview {
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
            System.out.println("Deleting Review object");

            session.beginTransaction();

            int reviewID = 2;
            Review reviewToDelete = session.get(Review.class,reviewID);

            if (reviewToDelete != null){
                session.delete(reviewToDelete);
            }else System.out.println("*No such ID*");

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
