package main.java.memoranda.gym;

import java.io.*;

/**
 * This class contains static methods for serializing and deserializing objects.
 */
public class ObjectSerializer implements Serializable {

    /**
     * Serialize object to file.
     * @param obj Object to be serialized
     * @param filepath File path object is to be saved to
     */
    public static void serializeObject(Object obj, String filepath) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);

    }

    /**
     * Deserialize object from file.
     * @param filepath File path object is to be extracted from
     * @return Object deserialized from file
     */
    public static Object deserializeObject(String filepath) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(filepath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();

    }

}
