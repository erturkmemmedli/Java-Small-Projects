package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtility {
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
