package pl.coderslab.Exercise5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FindMostPopularWords {

    public static void main(String[] args) {

        System.out.println("-------------------------------------------------------------------------- Input data ---");
        String[] inputUrls = {"http://www.interia.pl/", "http://www.onet.pl/"};
        String[] inputCssQueries = {"span.title", "span.title"};

        System.out.println("--------------------------------------------------------------- Transfer from website ---");
        String[] htmlTitles = getHtmlTitles(inputUrls, inputCssQueries);

        System.out.println("------------------------------------------------------------------------ Clear titles ---");
        String[] cleanedTitles = cleanSentences(htmlTitles);

        System.out.println("----------------------------------------------------------------------- Ignored words ---");
        ArrayList<String> ignoredWords = createIgnoredWords();

        System.out.println("----------------------------------------------------- Extract words without filtering ---");
        String[] words = getWordsFromTitles(cleanedTitles);

        System.out.println("----------------------------------------------------------------------- Write to file ---");
        writeToFile(words, "popular_words.txt");

        System.out.println("---------------------------------------------------------------------- Load from file ---");
        String[] popularWords = loadWordsFromFile("popular_words.txt");

        System.out.println("-------------------------------------------------------------- Print words in console ---");
        printArrayInConsole(popularWords);

        System.out.println("---------------------------------------------------------------- Filter popular words ---");
        String[] filteredPopularWords = filterWords(popularWords, ignoredWords);

        System.out.println("------------------------------------------------- Create list of unique popular words ---");
        String[] uniquePopularWords = new String[1];
        uniquePopularWords[0] = filteredPopularWords[0];
        int[] wordAttendance = new int[1];
        wordAttendance[0] = 1;
        int indexUniqueWords = 1;
        for (int n = 1; n < filteredPopularWords.length; n++) {
            if (!filteredPopularWords[n].equals(filteredPopularWords[n - 1])) {
                uniquePopularWords = Arrays.copyOf(uniquePopularWords, uniquePopularWords.length + 1);
                uniquePopularWords[indexUniqueWords] = filteredPopularWords[n];
                wordAttendance = Arrays.copyOf(wordAttendance, wordAttendance.length + 1);
                wordAttendance[indexUniqueWords] = 1;
                indexUniqueWords++;
            } else {
                wordAttendance[indexUniqueWords - 1]++;
            }
        }
        checkAmountOfWords(filteredPopularWords, wordAttendance);

        System.out.println("----------------------------------------- Make ranking with most popular words on top ---");
        String[] finalRanking = createFinalRanking(uniquePopularWords, wordAttendance);

        writeToFile(finalRanking, "filtered_popular_words.txt");
        printArrayInConsole(finalRanking);
    }

    public static void checkAmountOfWords(String[] filteredPopularWords, int[] wordAttendance) {
        int controlSum = 0;
        for (int num : wordAttendance) {
            controlSum += num;
        }
        if (controlSum == filteredPopularWords.length) {
            System.out.println("Proper number of unique words and their attendance. ");
        } else {
            System.out.println("Ups. Something went wrong.");
        }
    }

    // created methods @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public static ArrayList<String> createIgnoredWords() {
        ArrayList<String> ignoredWords = new ArrayList<>();
        ignoredWords.addAll(Arrays.asList("oraz", "ponieważ", "być", "nie", "pod", "zaraz", "tym", "tylko", "ten",
                "tego", "takim", "skąd", "się", "oto", "niż", "która", "które", "który", "jak", "dla", "dlaczego",
                "czy", "onet", "wp", "jest", "tak", "ale"));
        return ignoredWords;
    }

    public static String[] getHtmlTitles(String[] urls, String[] cssQueries) {
        String[] htmlTitles = new String[0];
        int i = 0;
        try {
            for (int j = 0; j < urls.length; j++) {
                Connection connect = Jsoup.connect(urls[j]);
                Document document = connect.get();
                Elements links = document.select(cssQueries[j]);
                for (Element elem : links) {
                    System.out.println(elem.text());
                    htmlTitles = Arrays.copyOf(htmlTitles, htmlTitles.length + 1);
                    htmlTitles[i] = elem.text();
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlTitles;
    }

    public static String[] cleanSentences(String[] sentences) {
        String[] cleanedSentences = new String[0];
        Arrays.sort(sentences);
        for (int i = 0; i < sentences.length; i++) {
            cleanedSentences = Arrays.copyOf(cleanedSentences, cleanedSentences.length + 1);
            cleanedSentences[i] = sentences[i]
                    .toLowerCase()
                    .replaceAll("[[-+.,–^':?!()0123456789/]]", "")
                    .replace('"', ' ');
            System.out.println("Sentence: " + i + ">>> " + cleanedSentences[i]);
        }
        return cleanedSentences;
    }

    public static String[] getWordsFromTitles(String[] cleanedTitles) {
        String[] words = new String[0];
        int i = 0;
        for (String line : cleanedTitles) {
            StringTokenizer tk = new StringTokenizer(line, " ");
            while (tk.hasMoreTokens()) {
                String token = tk.nextToken().trim();
                if (token.length() > 2) { // && !ignoredWords.contains(token)
                    words = Arrays.copyOf(words, words.length + 1);
                    words[i] = token;
                    i++;
                }
            }
        }
        System.out.println("In given list of titles there are words in amount: " + words.length);
        Arrays.sort(words);
        return words;
    }

    public static void writeToFile(String[] array, String fileName) {
        try {
            FileWriter file = new FileWriter(fileName);
            for (String word : array) {
                file.write(word + "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] loadWordsFromFile(String fileName) {
        String[] popularWords = new String[0];
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            int i = 0;
            while (scan.hasNextLine()) {
                popularWords = Arrays.copyOf(popularWords, popularWords.length + 1);
                popularWords[i] = scan.nextLine();
                i++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return popularWords;
    }

    public static void printArrayInConsole(String[] array) {
        for (int m = 0; m < array.length; m++) {
            System.out.println(array[m]);
        }
    }

    public static String[] filterWords(String[] popularWords, ArrayList<String> ignoredWords) {
        String[] filteredPopularWords = new String[0];
        int i = 0;
        for (String str : popularWords) {
            if (!ignoredWords.contains(str)) {
                filteredPopularWords = Arrays.copyOf(filteredPopularWords, filteredPopularWords.length + 1);
                filteredPopularWords[i] = str;
                i++;
            }
        }
        return filteredPopularWords;
    }

    public static String[] createFinalRanking(String[] uniquePopularWords, int[] wordAttendance) {
        String[] finalRanking = new String[0];
        for (int l = 0; l < uniquePopularWords.length; l++) {
            finalRanking = Arrays.copyOf(finalRanking, finalRanking.length + 1);
            finalRanking[l] = wordAttendance[l] + " times appears word: " + uniquePopularWords[l];
        }
        Arrays.sort(finalRanking, Collections.reverseOrder());
        return finalRanking;
    }

}

