package pl.coderslab.Exercise2;

import java.util.*;

public class SimulatorLotto {

    public static void main(String[] args) {
        gameLotto();
    }

    static void gameLotto() {
        int points = 0;
        Scanner scan = new Scanner(System.in);
        int[] winComb = new int[6];
        int[] usrComb = new int[6];
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));
        for (int i = 0; i < 6; i++) {
            winComb[i] = arr[i];
        }
//        Linia do testów
//        System.out.println("Oto wylosowane wartości Lotto: " + Arrays.toString(winComb));
        int count = 0;
        while (true) {
            System.out.println("Obstawiasz liczby od 1-49 włącznie. Podaj liczbę nr: " + (count + 1));
            int tmp = 0;
            try {
                tmp = scan.nextInt();
            } catch (Exception e) {
                scan.next();
            }
            if (0 < tmp && tmp < 50 && !arrayContainsValue(usrComb, tmp)) {
                usrComb[count] = tmp;
                count++;
            } else {
                System.out.println("Podano nieprawidłowe dane w postaci: \n" +
                        "- liczby z poza zakresu 1-49, \n" +
                        "- wcześniej wprowadzonej liczby, \n" +
                        "- ciągu znaków który nie jest liczbą.\n" +
                        "Spóbuj jeszcze raz.");
            }
            if (count == 6) {
                break;
            }
        }
        Arrays.sort(usrComb);
        Arrays.sort(winComb);
        System.out.println("Oto twój los: " + Arrays.toString(usrComb));
        for (int i = 0; i < 6; i++) {
            if (arrayContainsValue(winComb, usrComb[i])) {
                points++;
            }
        }
        System.out.println("Oto wylosowane wartości Lotto: " + Arrays.toString(winComb));
        System.out.println("W dzisiejszym losowaniu uzyskałeś " + points + " punktów.");
        if (points < 3) {
            System.out.println("Nic nie wygrałeś.");
        }
        if (points == 3) {
            System.out.println("Trafiłeś 3. Starczy ci na browar.");
        }
        if (points == 4) {
            System.out.println("Trafiłeś 4. Starczy ci na telefon.");
        }
        if (points == 5) {
            System.out.println("Trafiłeś 5. Starczy ci na auto. ");
        }
        if (points == 6) {
            System.out.println("Trafiłeś 6. Jak masz mocną głowę do pieniędzy to możesz żyć z odsetek, a jak nie to się stoczysz na dno. ");
        }
        System.out.println("END GAME");
    }

    private static boolean arrayContainsValue(int[] arr, int value) {
        for (int n : arr) {
            if (n == value) return true;
        }
        return false;
    }
}

