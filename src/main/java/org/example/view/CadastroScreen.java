package org.example.view;

import org.example.NavigationFrame;
import org.example.database.UserDatabase;

import javax.swing.*;
import java.awt.*;

public class CadastroScreen extends JPanel {

    private JTextField userField;
    private JPasswordField passField;

    public CadastroScreen(NavigationFrame navigationFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridy = 0;
        JLabel userLabel = new JLabel("Novo Usuário:");
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

        // Botão para cadastrar
        gbc.gridy = 4;
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(e -> registerUser(navigationFrame));
        add(registerButton, gbc);

        // Botão para voltar ao login
        gbc.gridy = 5;
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> navigationFrame.showScreen("Login")); // Troca para tela de login
        add(backButton, gbc);
    }

    private void registerUser(NavigationFrame navigationFrame) {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuário e senha não podem estar vazios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (UserDatabase.registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            navigationFrame.showScreen("Login"); // Troca para tela de login após cadastro
        } else {
            JOptionPane.showMessageDialog(this, "Já existe um usuário com esse nome", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
