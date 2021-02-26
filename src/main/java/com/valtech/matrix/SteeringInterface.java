package com.valtech.matrix;

public interface SteeringInterface {


    void moveLeft();

    void moveRight();

    void moveUp();

    void moveDown();

    void enterPressed();

    boolean isCrashed();

    default void method(){
        System.out.println("Hi");
    }

}
