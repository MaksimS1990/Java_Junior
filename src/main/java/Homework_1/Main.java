package Homework_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static Homework_1.Tools.getAverageEvenNumbers;
import static Homework_1.Tools.printlnList;


public class Main {
    public static void main(String[] args) {

        // Генерация массива

        Random rnd = new Random();
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 10; i++) numberList.add(rnd.nextInt(10));

        // Вывод исходного массива

        System.out.print("Изначальный массив: ");
        printlnList(numberList);

        // Вывод четных чисел (для контроля)

        System.out.print("Четные числа массива: ");
        printlnList(numberList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList()));

        // одсчет среднего четных чисел

        System.out.println("Среднее арифметическое четных чисел: " + getAverageEvenNumbers(numberList));

    }

}