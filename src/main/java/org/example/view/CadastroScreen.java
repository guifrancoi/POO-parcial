package org.example.view;

import org.example.NavigationFrame;
import org.example.controller.CadastroController;

import javax.swing.*;
import java.awt.*;

public class CadastroScreen extends JPanel {

    private final JTextField userField;
    private final JPasswordField passField;
    private final CadastroController cadastroController;

    public CadastroScreen(NavigationFrame navigationFrame) {
        this.cadastroController = new CadastroController();

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

        gbc.gridy = 4;
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(e -> onRegister(navigationFrame));
        add(registerButton, gbc);

        gbc.gridy = 5;
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> navigationFrame.showScreen("Login"));
        add(backButton, gbc);
    }

    private void onRegister(NavigationFrame navigationFrame) {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        boolean sucesso = cadastroController.cadastrarUsuario(username, password);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            navigationFrame.showScreen("Login");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar. Verifique se o usuário já existe ou se os campos estão vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
