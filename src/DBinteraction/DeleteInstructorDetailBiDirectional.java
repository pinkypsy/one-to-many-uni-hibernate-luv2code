package DBinteraction;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class DeleteInstructorDetailBiDirectional {

    public static void main(String[] args) throws ParseException {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
        System.out.println("Deleting InstructorDetail object");

        session.beginTransaction();

        int theId = 10;
        InstructorDetail instructorDetailToDelete = session.get(InstructorDetail.class,theId);

        if (instructorDetailToDelete != null){

            //remove deleted object from associations; break bidirectional chain
//            instructorDetailToDelete.getInstructor().setInstructorDetail(null);

            session.delete(instructorDetailToDelete);
            System.out.println("Deleting " + instructorDetailToDelete.getInstructor() + "...");
            System.out.println("Deleting " + instructorDetailToDelete + "...");
        }else System.out.println("*No such ID*");

        session.getTransaction().commit();

        System.out.println("Done!");

    }finally {
        session.close();
        sessionFactory.close();
    }
}
}
