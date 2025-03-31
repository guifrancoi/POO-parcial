package org.example;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public LoginScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Login");
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
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
        panel.add(userLabel, gbc);

        gbc.gridy = 1;
        userField = new JTextField(15);
        userField.setFont(fieldFont);
        panel.add(userField, gbc);

        gbc.gridy = 2;
        JLabel passLabel = new JLabel("Senha:");
        passLabel.setFont(labelFont);
        panel.add(passLabel, gbc);

        gbc.gridy = 3;
        passField = new JPasswordField(15);
        passField.setFont(fieldFont);
        panel.add(passField, gbc);

        gbc.gridy = 4;
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> login());
        panel.add(loginButton, gbc);

        gbc.gridy = 5;
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(e -> new RegisterScreen()); // Abre a tela de cadastro
        panel.add(registerButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void login() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (UserDatabase.validateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            new MainScreen(); // Abre a nova tela
            dispose(); // Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
