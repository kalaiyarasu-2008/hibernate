package com.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
 public static void main(String[] args) {
  SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
  Student student=new Student(101,"Kalai","kalai@gmail.com","Java");
  Session session=factory.openSession();
  try {
   session.beginTransaction();
   Student existing=session.get(Student.class,101);
   if(existing!=null){ session.remove(existing); session.flush(); }
   session.persist(student);
   session.getTransaction().commit();
   System.out.println("Student record inserted successfully.");
   System.out.println(student);
  }
  catch(Exception e){if(session.getTransaction().isActive())session.getTransaction().rollback();e.printStackTrace();}
  finally{session.close();}
  Session updateSession=factory.openSession();
  try {
   updateSession.beginTransaction();
   Student existing=updateSession.get(Student.class,101);
   if(existing!=null){existing.setCourse("Java Full Stack"); updateSession.getTransaction().commit(); System.out.println("Student record updated successfully."); System.out.println(existing);}
   else {updateSession.getTransaction().rollback();System.out.println("Student record not found.");}
  }
  catch(Exception e){if(updateSession.getTransaction().isActive())updateSession.getTransaction().rollback();e.printStackTrace();}
  finally{updateSession.close();}
  factory.close();
  com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
 }
}
