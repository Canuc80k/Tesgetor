package com.datastructure;

public class Pair {
    public Object firstElement;
    public Object secondElement;

    public Pair(Object firstElement, Object secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement; 
    }
    
    public Object getFirstElement() {
        return firstElement;
    }
    
    public Object getSecondElement() {
        return secondElement;
    }
}
