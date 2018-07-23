package pl.coderslab.Exercise4;

import java.util.Random;
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
        while (true) {
            String operator = "";
            int cubes = 1;
            int walls = 0;
            int modificator = 0;
            int posD = -1;
            System.out.println("Wpisz 'q' aby wyjść. Wprowadź kod rzutu 'xDy+z' lub 'xDy-z' (x-ilość rzutów, y-ilość ścian, z-wartość dodawana/odejmowana): ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.contains("D") && input.contains("+") && input.indexOf('D') < input.indexOf('+')) {
                posD = input.indexOf("D");
                if (posD != 0) {
                    cubes = Integer.parseInt(input.substring(0, posD));
                }
                int posPlus = input.indexOf("+");
                walls = Integer.parseInt(input.substring(posD + 1, posPlus));
                modificator = Integer.parseInt(input.substring(posPlus));
                if (!properWallsNumber(walls)) {
                    System.out.println("Nie ma takiej kostki. Spróbuj ponownie.");
                    operator = "w";
                }
            } else if (input.contains("D") && input.contains("-") && input.indexOf('D') < input.indexOf('-')) {
                posD = input.indexOf("D");
                if (posD != 0) {
                    cubes = Integer.parseInt(input.substring(0, posD));
                }
                int posMinus = input.indexOf("-");
                walls = Integer.parseInt(input.substring(posD + 1, posMinus));
                modificator = Integer.parseInt(input.substring(posMinus));
                if (!properWallsNumber(walls)) {
                    System.out.println("Nie ma takiej kostki. Spróbuj ponownie.");
                    operator = "w";
                }
            } else if (input.contains("D")) {
                posD = input.indexOf("D");
                if (posD != 0) {
                    cubes = Integer.parseInt(input.substring(0, posD));
                }
                walls = Integer.parseInt(input.substring(posD + 1));
                if (!properWallsNumber(walls)) {
                    System.out.println("Nie ma takiej kostki. Spróbuj ponownie.");
                    operator = "w";
                }
            } else if (input.equals("q")) {
                break;
            } else {
                System.out.println("Nieprawidłowe dane. Spróbuj ponownie.");
                operator = "w";
            }
            if (!operator.equals("w")) {
                combinationResult(cubes, walls, modificator);
            }
        }
    }

    static void combinationResult(int cubes, int walls, int modification) {
        Random generator = new Random();
        int result = 0;
        for (int i = 0; i < cubes; i++) {
            result += generator.nextInt(walls) + 1;
        }
        result += modification;
        System.out.println("Your throw: number of cubes: " + cubes +
                ", number of walls in each cube: " + walls +
                ", result modification: " + modification + ". You got: " + result + ".");
    }

    static boolean properWallsNumber(int walls) {
        boolean result = false;
        int[] availableValues = {3, 4, 6, 8, 10, 12, 20, 100};
        for (int value : availableValues) {
            if (walls == value) {
                result = true;
            }
        }
        return result;
    }
}

