package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesAndReviews {
    public static void main(String[] args) {
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
            System.out.println("Start transaction");

            int courseID = 10;

            Course theCourse = session.get(Course.class,courseID);

            if (theCourse != null) {
                System.out.println("theCourse: " + theCourse);

                System.out.println("Reviews: " + theCourse.getReviews());
            }else System.out.println("No such Course");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
