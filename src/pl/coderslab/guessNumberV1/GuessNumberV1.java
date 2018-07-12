package pl.coderslab.guessNumberV1;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberV1 {

    public static void main(String[] args) {
        gameV1();
    }

    static String gameV1() {
        String result = null;
        Random generator = new Random();
        Scanner scan = new Scanner(System.in);
        int correctNumber = generator.nextInt(100) + 1;
        System.out.println("Zgadnij liczbę z zakresu 1-100: ");
        while (true) {
            int getValue = scan.nextInt();
            if (getValue < correctNumber) {
                result = "Podana liczba jest mniejsza niż szukana. Spróbuj ponownie";
                System.out.println(result);
            } else if (getValue > correctNumber) {
                result = "Podana liczba jest większa niż szukana. Spróbuj ponownie";
                System.out.println(result);
            } else {
                result = "Zgadłeś!";
                System.out.println(result);
                break;
            }
        }
        return result;
    }
}
