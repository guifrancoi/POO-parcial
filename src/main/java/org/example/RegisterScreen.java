package org.example;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public RegisterScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Cadastro de Usuário");
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
        JLabel userLabel = new JLabel("Novo Usuário:");
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
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(e -> registerUser());
        panel.add(registerButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void registerUser() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuário e senha não podem estar vazios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (UserDatabase.registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            dispose(); // Fecha a tela de cadastro
        } else {
            JOptionPane.showMessageDialog(this, "Já existe um usuário com esse nome!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
