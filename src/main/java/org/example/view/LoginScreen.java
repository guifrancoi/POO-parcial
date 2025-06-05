package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton cadastrarButton;

    public LoginScreen() {
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

        gbc.gridy = 4;
        loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(loginButton, gbc);

        gbc.gridy = 5;
        cadastrarButton = new JButton("Criar Conta");
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(cadastrarButton, gbc);
    }

    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return new String(passField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addCadastrarListener(ActionListener listener) {
        cadastrarButton.addActionListener(listener);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
