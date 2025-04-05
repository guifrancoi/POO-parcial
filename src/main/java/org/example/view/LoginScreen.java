package org.example.view;

import org.example.NavigationFrame;
import org.example.database.UserDatabase;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {

    private JTextField userField;
    private JPasswordField passField;

    public LoginScreen(NavigationFrame navigationFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridy = 0;
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setFont(labelFont);
        add(userLabel, gbc);

        gbc.gridy = 1;
        userField = new JTextField(15);
        userField.setFont(fieldFont);
        add(userField, gbc);

        gbc.gridy = 2;
        JLabel passLabel = new JLabel("Senha:");
        passLabel.setFont(labelFont);
        add(passLabel, gbc);

        gbc.gridy = 3;
        passField = new JPasswordField(15);
        passField.setFont(fieldFont);
        add(passField, gbc);

        // Botão de login
        gbc.gridy = 4;
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> login(navigationFrame));
        add(loginButton, gbc);

        // Botão para criar conta
        gbc.gridy = 5;
        JButton registerButton = new JButton("Criar Conta");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(e -> navigationFrame.showScreen("Cadastro")); // Troca para tela de cadastro
        add(registerButton, gbc);
    }

    private void login(NavigationFrame navigationFrame) {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (UserDatabase.validateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            new MainScreen();
            navigationFrame.dispose(); // Fecha a janela principal após o login
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
