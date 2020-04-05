package main.java.memoranda.util;
 
 import nu.xom.Element;
 
 public class Pair {
   private Element element;
   private int priority;

   /**
    * Constructorr to build a Pair based on value and priority passed in
    * @param value
    * @param priority
    */
   public Pair(Element value, int priority){
     setElement(value);
     setPriority(priority);
   }

   /**
    * Method to get current Element from the Pair
    * @return Element
    */
   public Element getElement() {
     return element;
   }

   /**
    * Method to set current Element's value
    * @param value
    */
   public void setElement(Element value) {
     this.element = value;
   }

   /**
    * Method to get priority of the current Pair
    * @return
    */
   public int getPriority() {
     return priority;
   }

   /**
    * Method to set Priority of the current Pair
    * @param priority
    */
   public void setPriority(int priority) {
     this.priority = priority;
   }
 
 }