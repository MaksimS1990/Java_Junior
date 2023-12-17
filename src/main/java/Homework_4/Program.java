package Homework_4;

import models.Courses;

import javax.crypto.Cipher;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program {

    private final static Random random = new Random();

    public static void main(String[] args) {

        String URL = "jdbc:mysql://localhost:13306/";
        String USER = "root";
        String PASSWORD = "password";

            // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {


            // Создание базы данных
            createDatabase(connection);
            System.out.println("Database created successfully");

            // Использование базы данных
            useDatabase(connection);
            System.out.println("Use database successfully");

            // Создание таблицы
            createTable(connection);
            System.out.println("Create table successfully");

            // Добавление данных
            int count = random.nextInt(1, 2);
            for (int i = 0; i < count; i++)
                insertData(connection, Courses.create());
            System.out.println("Insert data successfully");

            // Чтение данных
            Collection<Courses> courses = readData(connection);
            for (var course: courses)
                System.out.println(course);
            System.out.println("Read data successfully");

            // Обновление даных
            for (var course: courses) {
                course.updateTitle();
                course.updateDuration();
                updateData(connection, course);
            }
            System.out.println("Update data successfully");

            // Чтение данных
            courses = readData(connection);
            for (var course: courses)
                System.out.println(course);
            System.out.println("Read data successfully");

            // Удаление данных
            for (var course: courses)
                deleteData(connection, course.getId());
            System.out.println("Delete data successfully");

            // Чтение данных
            courses = readData(connection);
            for (var course: courses)
                System.out.println(course);
            System.out.println();
            System.out.println("Read data successfully(NOTHING FOR READ)");

            // Закрытие соединения
            connection.close();
            System.out.println("Database connection close successfully");

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

        // region Вспомогательные методы

        private static void createDatabase(Connection connection) throws SQLException {
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS SchoolDB;";
            try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
                statement.execute();
            }
        }

        private static void useDatabase (Connection connection) throws SQLException {
            String useDatabaseSQL = "USE SchoolDB";
            try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
                statement.execute();
            }
        }

        private static void createTable (Connection connection) throws SQLException {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(45), duration INT);";
            try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
                statement.execute();
            }
        }

    /**
     *  Добавление данных в таблицу
     * @param connection Соединение с БД
     * @param courses Курс
     * @throws SQLException Исключение при выполнении запроса
     */

        private static void insertData(Connection connection, Courses courses) throws SQLException {
            String insertDataSQL = "INSERT INTO Courses (title, duration) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
                statement.setString(1, courses.getTitle());
                statement.setInt(2, courses.getDuration());
                statement.executeUpdate();
            }
        }

    /**
     * Чтение данных из таблицы Courses
     * @param connection Соединение с БД
     * @return Коллекция курсов
     * @throws SQLException Исключение при выполнении запроса
     */

    private static Collection<Courses> readData(Connection connection) throws SQLException {
        ArrayList<Courses> coursesList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM Courses";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                coursesList.add(new Courses(id, title, duration));
            }
            return coursesList;
        }
    }

    /**
     * Обновление данных в таблице Courses по идентификатору
     * @param connection соединение с БД
     * @param courses Курс
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void updateData(Connection connection, Courses courses) throws SQLException {
        String updateDataSQL = "UPDATE Courses SET title=?, duration=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, courses.getTitle());
            statement.setInt(2, courses.getDuration());
            statement.setInt(3, courses.getId());
            statement.executeUpdate();
        }
    }

    /**
     * Удаление записи из таблицы Courses по идентификатору
     * @param connection Соединение с БД
     * @param id Идентификатор записи
     * @throws SQLException Исключение при выполнении запроса
     */

    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM Courses WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // endregion

}
