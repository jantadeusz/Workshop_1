package pl.coderslab.Exercise4;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Scanner;


public class GameCube {

    public static void main(String[] args) {
        gameCube();
    }

    static void gameCube() {
        System.out.println("Witaj w symulatorze rzutów kośćmi. Dostępne są następujące typy kości: \n" +
                "- D3 kostka 3 ścienna \n" +
                "- D4 kostka 4 ścienna \n" +
                "- D6 kostka 6 ścienna \n" +
                "- D8 kostka 8 ścienna \n" +
                "- D10 kostka 10 ścienna \n" +
                "- D12 kostka 12 ścienna \n" +
                "- D20 kostka 20 ścienna \n" +
                "- D100 kostka 100 ścienna");
        System.out.println("Wprowadź kod rzutu 'xDy+z' lub 'xDy-z' (x-ilość rzutów, y-ilość ścian, z-wartość dodawana/odejmowana): ");
        String newThrow;

        String[] splittedThrow;
//        while (true) {
        Scanner scan = new Scanner(System.in);
        newThrow = scan.nextLine();
//        int posD = newThrow.indexOf('D');
//            int posPlus = newThrow.indexOf('+');
//            int posMinus = newThrow.indexOf('-');
        if (newThrow.contains("D") && newThrow.contains("+") && newThrow.indexOf('D') < newThrow.indexOf('+')) {
            String[] tmp = StringUtils.split(newThrow, "D");
            String[]  tmp1 = tmp[1].split("\\+");
//            String[] tmp1= StringUtils.split(tmp[1],"+");


        } else if (newThrow.contains("D") && newThrow.contains("-") && newThrow.indexOf('D') < newThrow.indexOf('-')) {
            String tmp[] = StringUtils.split(newThrow, "D");

        } else {
            System.out.println("Nieprawidłowe dane");
        }
        System.out.println(Arrays.toString(tmp));
        System.out.println(tmp[0]);
        String amountOfCubes = tmp[0];
        String restOfThrow = tmp[1];

        System.out.println("Amount of cubes: " + amountOfCubes);
        if (newThrow.contains("D")) {
            splittedThrow = newThrow.split("D");
            System.out.println(Arrays.toString(splittedThrow));
            if (splittedThrow[1].contains("+")) {
//                String[] finalThrow;
//                finalThrow[1] = splittedThrow[1].split("\\+");

            }

            if (newThrow.contains("+")) {
//            break;
            } else if (newThrow.contains("-")) {

            } else {
                System.out.println("Wprowadzono nieprawidłowe dane. Jeszcze raz: ");
                scan.next();
            }

        }


    }
}

