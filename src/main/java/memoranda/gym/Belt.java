package main.java.memoranda.gym;

import java.util.HashMap;


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

    private Belt(int value){
        _value = value;
    }

    static {
        for (Belt belt:Belt.values()){
            belts.put(belt._value,belt);
        }
    }

    public static Belt getBelt(int value){
        return (Belt) belts.get(value);
    }

    public int getValue(){
        return _value;
    }



}
