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
        String input;
        while (true) {
            int guess = (max - min) / 2 + min;
            System.out.println("Zgaduję: " + guess);
            i++;
            System.out.println("Zgadłem? (wpisz w konsoli: 'tak' lub 'nie'): ");
            input = scan.nextLine();
            if (input.equals("tak")) {
                break;
            } else if (input.equals("nie")) {
                System.out.println("Za dużo? (wpisz w konsoli: '1'-tak, '2'-nie): ");
                input = scan.nextLine();
                if (input.equals("tak")) {
                    max = guess;
                } else if (input.equals("nie")) {
                    System.out.println("Za mało? (wpisz w konsoli: '1'-tak, '2'-nie): ");
                    input = scan.nextLine();
                    if (input.equals("tak")) {
                        min = guess;
                    } else if (input.equals("nie")) {
                        System.out.println("Nie oszukuj!");
                        i--;
                    } else {
                        System.out.println("Nie podano poprawnego wyrażenia : 'tak' / 'nie'. Spóbuj jeszcze raz.");
                        i--;
                    }
                } else {
                    System.out.println("Nie podano poprawnego wyrażenia : 'tak' / 'nie'. Spóbuj jeszcze raz.");
                    i--;
                }
            } else {
                System.out.println("Nie podano poprawnego wyrażenia : 'tak' / 'nie'. Spóbuj jeszcze raz.");
                i--;
            }
        }
        System.out.println("Zgadłem w " + i + " próbach.");
        System.out.println("END GAME");
    }
}

