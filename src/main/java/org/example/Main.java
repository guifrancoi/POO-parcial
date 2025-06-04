package org.example;

import org.example.controller.MainScreenController;
import org.example.view.MainScreen;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NavigationFrame frame = new NavigationFrame();
            frame.showScreen("Login");
        });
    }
}