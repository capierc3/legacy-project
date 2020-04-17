package main.java.memoranda.gym;

import java.util.HashMap;

/**
 * Enum Class for the Belts, Each are assigned a value depending on their ordering.
 * White belt ->0 is lower than Blue ->4.
 *
 * You are able be able to get the value of the enum with the getValue() method.
 *
 * You are also able to retrieve the enum with inputting its value with getBelt(int value) method.
 * This allows you to save the value of the enum in the element and quickly turn it back into the enum.
 *
 * @author Chase
 */
public enum Belt {
    WHITE(0),
    YELLOW(1),
    ORANGE(2),
    PURPLE(3),
    BLUE(4),
    BLUE_STRIPE(5),
    GREEN(6),
    GREEN_STRIPE(7),
    BROWN1(8),
    BROWN2(9),
    BROWN3(10),
    BLACK1(11),
    BLACK2(12),
    BLACK3(13);

    private int _value;
    private static HashMap belts = new HashMap();

    /**
     * Constructor that adds the value to the enum.
     * @param value int
     */
    Belt(int value){
        _value = value;
    }

    static {
        for (Belt belt:Belt.values()){
            belts.put(belt._value,belt);
        }
    }

    /**
     * returns the enum with the associated int value.
     * @param value int
     * @return Belt
     */
    public static Belt getBelt(int value){
        return (Belt) belts.get(value);
    }

    /**
     * returns the value associated with the enum.
     * @return int
     */
    public int getValue(){
        return _value;
    }



}
