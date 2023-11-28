package Homework_1;

import java.util.List;

public class Tools {

    // Подсчет среднего занчения всех четных чисел в массива

    public static Double getAverageEvenNumbers(List<Integer> numberList) {
        if (numberList==null || numberList.isEmpty())return null;
        return numberList.stream().mapToInt(Integer.class::cast)
                .filter(i -> i % 2 == 0)
                .average().orElse(0);
    }

     // Вывод массива на печать в виде [E0, E1, E2, ... En] без замыкающего

    public static <E> void printList(List<E> numberList) {
        if (numberList == null || numberList.isEmpty()) {
            System.out.print("[]");
            return;
        }

        System.out.print("[");
        numberList.stream()
                .limit(numberList.size() - 1)
                .forEach(e -> System.out.print(e + ", "));
        numberList.stream()
                .skip(numberList.size() - 1)
                .forEach(e -> System.out.print(e + "]"));
    }


     // Вывод массива на печать в виде [E0, E1, E2, ... En] с замыкающим

    public static <E> void printlnList(List<E> numberList) {
        printList(numberList);
        System.out.println();
    }
}
