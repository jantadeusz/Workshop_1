package pl.coderslab.Exercise3;

import java.util.Scanner;

public class GuessNumberV2 {
    public static void main(String[] args) {
        gameV2();
    }

    static void gameV2() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max. 10 próbach. ");
        int min = 0;
        int max = 1000;
        int i = 0;
        int input = 0;
        while (true) {
            int guess = (max - min) / 2 + min;
            System.out.println("Zgaduję: " + guess);
            System.out.println("Pobierz odpowiedź z zestawu:  za dużo, za mało, zgadłeś.");
            i++;
            System.out.println("Zgadłem? (wpisz w konsoli: '1'-tak, '2'-nie): ");
            input = scan.nextInt();
            if (input == 1) {
                break;
            }
            if (input == 2) {
                System.out.println("Za dużo? (wpisz w konsoli: '1'-tak, '2'-nie): ");
                input = scan.nextInt();
                if (input == 1) {
                    max = guess;
                }
                if (input == 2) {
                    System.out.println("Za mało? (wpisz w konsoli: '1'-tak, '2'-nie): ");
                    input = scan.nextInt();
                    if (input == 1) {
                        min = guess;
                    }
                    if (input == 2) {
                        System.out.println("Nie oszukuj!");
                        i--;
                    }
                }
            }
        }
        System.out.println("Wygrałem!");
        System.out.println("Zgadłem w " + i + " próbach.");
        System.out.println("END GAME");
    }
}
