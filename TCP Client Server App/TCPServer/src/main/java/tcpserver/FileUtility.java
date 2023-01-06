package tcpserver;

import java.io.*;

public class FileUtility {
    public static void writeIntoFile(String fileName, String text, boolean append) throws Exception {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append));) {
            bw.write(text);
        }
    }

    public static void writeIntoFile(String fileName, String text) throws Exception {
        writeIntoFile(fileName, text, false);
    }

    public static void appendIntoFile(String fileName, String text) throws Exception {
        writeIntoFile(fileName, text, true);
    }

    public static void writeBytes(String fileName, byte[] data) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(data);
        fos.flush();
        fos.close();
        System.out.println("Done!");
    }

    public static String read(String fileName) throws Exception {
        try(InputStream in = new FileInputStream(fileName);
            InputStreamReader r = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(r);) {
            String line = null;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
            return result;
        }
    }

    public static byte[] readBytes(String fileName) throws Exception {
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file);) {
            byte[] byteArray = new byte[(int) file.length()];
            fis.read(byteArray);
            return byteArray;
        }
    }

    public static Object readFileDeserialize(String name) {
        Object obj = null;
        try (FileInputStream fis = new FileInputStream(name); ObjectInputStream in = new ObjectInputStream(fis)) {
            obj = in.readObject();
        } finally {
            return obj;
        }
    }

    public static boolean writeObjectToFile(Object object, String name) throws RuntimeException {
        try (FileOutputStream fos = new FileOutputStream(name); ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(object);
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}