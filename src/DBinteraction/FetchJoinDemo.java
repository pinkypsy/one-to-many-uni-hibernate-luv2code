package DBinteraction;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {
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

            Query <Instructor> query = session.createQuery("select i from Instructor i " +
                    "JOIN FETCH i.courses " +
                    "where i.id=:theInstructorId", Instructor.class);

            query.setParameter("theInstructorId", theId);

            Instructor theInstructor = query.getSingleResult();

            session.close();
            System.out.println("\n Session closed \n");

            System.out.println("Courses fetched: " + theInstructor.getCourses());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
