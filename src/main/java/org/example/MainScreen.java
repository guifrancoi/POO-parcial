package org.example;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen() {
        setTitle("Home");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setTitle("Tela Principal");
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Bem-vindo ao sistema!", SwingConstants.CENTER);
        add(welcomeLabel);

        setVisible(true);

    }

}
