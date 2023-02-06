package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileUtility {
    public static void read(HashMap<String, String> engAze, HashMap<String, String> azeEng, String fileName) throws Exception {
        try(InputStream inputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);)
        {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordPairs = line.split(",");
                engAze.put(wordPairs[0].trim(), wordPairs[1].trim());
                azeEng.put(wordPairs[1].trim(), wordPairs[0].trim());
            }
        }
    }
}