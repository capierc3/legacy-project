package main.java.memoranda.gym;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.*;

/**
 * This class contains static methods for serializing and deserializing objects.
 */
public class ObjectSerializer implements Serializable {

    /**
     * serializes an element to an XML file to an inputted filepath.
     * @param element Element
     * @param filepath String
     * @throws IOException e
     */
    public static void serializeElement(Element element, String filepath) throws IOException {
        Element el = (Element) element.copy();
        Document writeDoc = new Document(el);
        OutputStream fileOutputStream = new FileOutputStream(filepath);
        Serializer serializer = new Serializer(fileOutputStream, "UTF-8");
        serializer.setIndent(4);
        serializer.setMaxLength(64);
        serializer.write(writeDoc);
        serializer.flush();
    }

}
