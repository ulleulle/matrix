package com.valtech.matrix;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.List;
import java.util.logging.LogManager;

public class Main implements NativeKeyListener, MenuInterface{


    private DisplayInterface currentDisplay;
    private SteeringInterface currentSteering;

    public static void main(String[] args) throws InterruptedException {
        new Main();
    }

    public Main () throws InterruptedException {
        Menu menu = new Menu(this);
        setCurrentInterfaces(menu);

        hookKeyPresses();
        RenderLoop();
    }

    private void hookKeyPresses() {
        try {
            GlobalScreen.registerNativeHook();
            LogManager.getLogManager().reset();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(this);
    }

    private void RenderLoop() throws InterruptedException {
        while (true) {
            List<List<String>> fullFrame = currentDisplay.getFullFrame();
            Main.clear();
            Main.print(fullFrame);
            Thread.sleep(200);
        }
    }


    public void setCurrentInterfaces(Object object){
        if(object instanceof DisplayInterface) {
            currentDisplay = (DisplayInterface) object;
        }
        currentDisplay.setMatrixDimensions(15, 40);
        if(object instanceof SteeringInterface) {
            currentSteering = (SteeringInterface) object;
        }
    }

    private static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void print(List<List<String>> fullFrame) {
        for (List<String> strings : fullFrame) {
            StringBuilder builder = new StringBuilder(strings.size());
            for (String string : strings) {
                builder.append(string);
            }
            System.out.println(builder.toString());
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(currentSteering != null) {
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_LEFT) {
                currentSteering.moveLeft();
            }
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
                currentSteering.moveRight();
            }
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_UP) {
                currentSteering.moveUp();
            }
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_DOWN) {
                currentSteering.moveDown();
            }
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ENTER) {
                currentSteering.enterPressed();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void selectedApplication(String selectedApplication) {
        if(selectedApplication.equals(Racing.class.toString())){
            setCurrentInterfaces(new Racing());
        }
        if(selectedApplication.equals(Matrix.class.toString())){
            setCurrentInterfaces(new Matrix());
        }
    }
}
