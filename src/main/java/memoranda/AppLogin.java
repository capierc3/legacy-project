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
    private HashMap<String, Object> getFromFile(String file_path) {

        HashMap<String, Object> map = new HashMap<>();

        try {

            FileInputStream fileInputStream = new FileInputStream(file_path);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            map = (HashMap<String, Object>) inputStream.readObject();

        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return map;

    }

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
