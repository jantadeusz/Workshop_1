package pl.coderslab.Exercise1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberV1 {

    public static void main(String[] args) {
        gameV1();
    }

    static String gameV1() {
        String result;
        Random generator = new Random();
        Scanner scan = new Scanner(System.in);
        int correctNumber = generator.nextInt(100) + 1;
        System.out.println("Zgadnij liczbę z zakresu 1-100: ");
        while (true) {
            try {
                int getValue = scan.nextInt();
                if (getValue < correctNumber) {
                    result = "Za mało!";
                    System.out.println(result);
                } else if (getValue > correctNumber) {
                    result = "Za dużo!";
                    System.out.println(result);
                } else {
                    result = "Zgadłeś!";
                    System.out.println(result);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("To nie jest liczba");
                System.out.println("Zgadnij liczbę z zakresu 1-100: ");
                scan.nextLine();
            }
        }
        return result;
    }
}
