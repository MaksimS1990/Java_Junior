package Homework_2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        Animal[] animals = {
                new Dog("Артур", 12),
                new Cat("Вениамин", 5),
                new Dog("Лёлик", 9),
                new Cat("Август", 6),
                new Cat("Селена", 10),
                new Dog("Варгас", 7),
                new Cat("Авраам", 14)
        };

        Set<Class<?>> classSet = new HashSet<>();
        for (Animal a : animals) {
            classSet.add(a.getClass());
        }
        for (Class<?> clazz : classSet) {
            System.out.println("Класс: " + clazz.getName());
            System.out.println("Поля класса :");
            Arrays.stream(clazz.getDeclaredFields())
                    .forEach(field -> System.out.println("\t" + field.getName()));
            Arrays.stream(clazz.getSuperclass().getDeclaredFields())
                    .forEach(field -> System.out.println("\t" + field.getName()));
            System.out.println("Методы класса :");
            Arrays.stream(clazz.getDeclaredMethods())
                    .forEach(field -> System.out.println("\t" + field.getName()));
            Arrays.stream(clazz.getSuperclass().getDeclaredMethods())
                    .forEach(field -> System.out.println("\t" + field.getName()));
            System.out.println();
            for (Animal all : animals) {
                System.out.println(all);
                try {
                    Method makeSoundMethod = all.getClass().getMethod("makeSound");
                    ((Method) makeSoundMethod).invoke(all);
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ignored) {
                }
            }

        }
    }
}