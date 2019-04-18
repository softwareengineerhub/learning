package com.app.ch06.io.app04.printstream;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            String s = null;
            s.trim();
        } catch (Exception ex) {
            showError(ex);
        }
        System.out.println("-------------------");
        PrintStream old = System.out;
        System.setOut(new PrintStream(new File("printstream")));
        System.out.println("Hello");
        System.out.println("This is println");
        System.setOut(old);
        System.out.println("--------Console again-----------");
    }

    private static void showError(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        ex.printStackTrace(ps);
        ex.printStackTrace();
        byte[] data = out.toByteArray();
        String msg = new String(data);
        JOptionPane.showMessageDialog(null, msg);
    }
}
