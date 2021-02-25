package com.valtech.matrix;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;

public class Main implements NativeKeyListener{
    private static Snake matrix = new Snake();
    private static Main currentMain;


    public Main (){
        Menu menu = new Menu();

    }
    public static void main(String[] args) throws InterruptedException {
        currentMain = new Main();

        matrix.setMatrixDimensions(15, 40);
        try {
            GlobalScreen.registerNativeHook();
            LogManager.getLogManager().reset();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        int i = 0;
        GlobalScreen.addNativeKeyListener(new Main());
        while (true) {
            List<List<String>> fullFrame = matrix.getFullFrame();
            Main.clear();
            Main.print(fullFrame);
            Thread.sleep(200);
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
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_LEFT) {
            matrix.moveLeft();
        }
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
            matrix.moveRight();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
