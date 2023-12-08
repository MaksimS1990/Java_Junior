package Homework_3;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("  Первичный объект  ");
        Student studentToSave = new Student("Дамблдор", 99, 99.9);
        System.out.println(studentToSave);
        FileOutputStream out = new FileOutputStream("Hogvarts.bin");
        ObjectOutputStream objectOut = new ObjectOutputStream(out);

        objectOut.writeObject(studentToSave);
        objectOut.close();

        System.out.println("  Прочитанный объект  ");
        Student studentToRead;

        FileInputStream in = new FileInputStream("Hogvarts.bin");
        ObjectInputStream objectIn = new ObjectInputStream(in);

        studentToRead = (Student) objectIn.readObject();
        System.out.println(studentToRead);
    }

}
