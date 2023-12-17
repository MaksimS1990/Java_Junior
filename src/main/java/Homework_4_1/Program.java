package Homework_4_1;

import jdk.swing.interop.SwingInterOpUtils;
import models.Courses;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Program {

    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */

    public static void main(String[] args) {

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Courses.class)
                .buildSessionFactory()) {
            // Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Courses course = Courses.create();
            session.save(course);
            System.out.println("Object course save successfully");

            // Чтение обьекта из БД
            Courses retrievedCourses = session.get(Courses.class, course.getId());
            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourses);

            // Обновление обьекта
            retrievedCourses.updateTitle();
            retrievedCourses.updateDuration();
            session.update(retrievedCourses);
            System.out.println("Object course update successfully");

            // Удаление обьекта
            session.delete(retrievedCourses);
            System.out.println("Object course delete successfully");

            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
