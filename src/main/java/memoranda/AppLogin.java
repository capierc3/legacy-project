package main.java.memoranda;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.HashMap;

public class AppLogin {
    private final String APP_USER_FILE_PATH = "/appusers.dat";
    private HashMap<String, Object> appUsers;

    public AppLogin(){

    }

    public Object getUser(String login, String password) {
        //TODO: Fetch User
        String key = login + ":" + password;
        return appUsers.get(key);
    }

    public void addUser(Object user) {

        //TODO: Add User

    }


    /**
     * Purpose: Construct appUser collection from file
     * @return HashMap<String, Object>
     * @param file_path path of file containing appUser collection
     * */
    private Object getFromFile(String file_path) {

        Object obj = null;

        try {

            FileInputStream fileInputStream = new FileInputStream(file_path);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            obj = inputStream.readObject();

        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;

    }

    /**
     * Purpose: Convert object to byte array and save to file
     * @param object object to be saved to file
     * @param file_path path of file containing appUser collection
     * */
    private void saveToFile(Object object, String file_path) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file_path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(object);
            outputStream.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
