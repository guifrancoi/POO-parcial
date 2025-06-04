package org.example.view;

import org.example.NavigationFrame;
import org.example.controller.LoginController;
import org.example.controller.MainScreenController;
import org.example.model.entity.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {

    private JTextField userField;
    private JPasswordField passField;
    private final LoginController loginController = new LoginController();

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

        gbc.gridy = 4;
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> realizarLogin(navigationFrame));
        add(loginButton, gbc);

        gbc.gridy = 5;
        JButton registerButton = new JButton("Criar Conta");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(e -> navigationFrame.showScreen("Cadastro"));
        add(registerButton, gbc);
    }

    private void realizarLogin(NavigationFrame navigationFrame) {
        String username = userField.getText();
        String senha = new String(passField.getPassword());

        Usuario usuario = loginController.autenticar(username, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            MainScreen mainScreen = new MainScreen(usuario);
            new MainScreenController(mainScreen, usuario);
            mainScreen.setVisible(true);
            navigationFrame.setVisible(false);
            navigationFrame.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
