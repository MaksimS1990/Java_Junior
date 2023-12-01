package Homework_2;

abstract class Animal {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Питомец{" +
                "name = '" + name + '\'' +
                ", age = " + age + " годиков" +
                '}';
    }
}