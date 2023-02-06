package main;

import util.FileUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // initialization of parameters
        String fileName = "C:/Users/Erturk Memmedli/Documents/Workspace/Dictionary Reading App/words.txt";
        HashMap<String, String> dictionaryAzeEng = new HashMap<>();
        HashMap<String, String> dictionaryEngAze = new HashMap<>();
        // creating dictionary by reading from file
        FileUtility.read(dictionaryEngAze, dictionaryAzeEng, fileName);
        int wordCount = dictionaryAzeEng.size();
        // starting answer-question session
        Scanner scanner = new Scanner(System.in);
        boolean notSelected = true;
        while (notSelected) {
            // asking client to choose between English or Azerbaijani
            System.out.println("Sual soruşulacaq dili seçin. / Choose the language for question.");
            System.out.println("Ingilis dili üçün 'E', Azərbaycan dili üçün 'A' yazın. / Write 'E' for English or 'A' for Azerbaijani.");
            String language = scanner.nextLine();
            // asking question, depending on the selection
            int correctAnswer = 0;
            if (language.equals("E")) {
                for (Map.Entry<String, String> set : dictionaryEngAze.entrySet()) {
                    int rightOfReply = 3;
                    while (rightOfReply > 0) {
                        System.out.println("Azərbaycan dilində " + set.getKey() + " nədir? " + rightOfReply + " cavab haqqınız var.");
                        String answer = scanner.nextLine();
                        if (answer.equals(set.getValue())) {
                            System.out.println("Təbriklər. Cavab düzgündür.");
                            correctAnswer++;
                            break;
                        } else {
                            rightOfReply--;
                        }
                    }
                    if (rightOfReply == 0) {
                        System.out.println("Düzgün cavabı tapa bilmədiniz. Düzgün cavab: " + set.getValue() + ".");
                    }
                }
                double gameResult = (double) correctAnswer / (double) wordCount * 100;
                System.out.println("Lüğətdəki bütün sözləri bitirdiniz. Cavablarınız " + String.format("%.2f", gameResult) + "% düz idi.");
                System.out.println("Təkrar oynamaq istəyirsinizsə, 'ok' yazın, əks halda oyun bitəcək.");
                String response = scanner.nextLine();
                if (! response.equals("ok")) {
                    notSelected = false;
                }
            } else if (language.equals("A"))  {
                for (Map.Entry<String, String> set : dictionaryAzeEng.entrySet()) {
                    int rightOfReply = 3;
                    while (rightOfReply > 0) {
                        System.out.println("What is " + set.getKey() + " in English? You have " + rightOfReply + " right of reply.");
                        String answer = scanner.nextLine();
                        if (answer.equals(set.getValue())) {
                            System.out.println("Congratulations. The answer is correct.");
                            correctAnswer++;
                            break;
                        } else {
                            rightOfReply--;
                        }
                    }
                    if (rightOfReply == 0) {
                        System.out.println("You couldn't find the correct answer. The correct answer: " + set.getValue() + ".");
                    }
                }
                double gameResult = (double) correctAnswer / (double) wordCount * 100;
                System.out.println("You have finished all the words in dictionary. Your answers were " + String.format("%.2f", gameResult) + "% correct.");
                System.out.println("If you want to play again 'ok'; otherwise, game will end.");
                String response = scanner.nextLine();
                if (! response.equals("ok")) {
                    notSelected = false;
                }
            } else {
                System.out.println("Xətalı seçim. Lütfən qaydalara uyğun seçim edin.");
            }
        }
    }
}