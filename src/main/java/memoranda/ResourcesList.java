package main.java.memoranda;

import java.util.Vector;

import nu.xom.Document;
/*$Id: ResourcesList.java,v 1.4 2007/03/20 06:21:46 alexeya Exp $*/
/**
 * Interface for the list of resources
 */
public interface ResourcesList {
    /**
     * returns all the resources in the list
     * @return Vector
     */
    Vector getAllResources();
    
    //Vector getResourcesForTask(String taskId);

    /**
     * returns a single resource form the list
     * @param path String
     * @return Resource
     */
    Resource getResource(String path);

    /**
     * adds a resource to the list.
     * @param path String
     * @param isInternetShortcut boolean
     * @param isProjectFile boolean
     */
    void addResource(String path, boolean isInternetShortcut, boolean isProjectFile);

    /**
     * adds a resource to a list with only the path
     * @param path String
     */
    void addResource(String path);
    
    //void addResource(String path, String taskId);

    /**
     * removes a resource with matching path
     * @param path String
     */
    void removeResource(String path);

    /**
     * getter for the amount of resources
     * @return int
     */
    int getAllResourcesCount();

    /**
     * getter for the main XML Document
     * @return Document
     */
    Document getXMLContent();

}
