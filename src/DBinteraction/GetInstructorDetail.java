package DBinteraction;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetail {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            System.out.println("Start transaction");

            int theId = 4;

            InstructorDetail theInstructorDetail = session.get(InstructorDetail.class,theId);

            System.out.println("Instructor: " + theInstructorDetail.getInstructor());


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
