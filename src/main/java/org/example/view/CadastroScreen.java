package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroScreen extends JPanel {

    private final JTextField userField;
    private final JPasswordField passField;
    private final JButton cadastrarButton;
    private final JButton backButton;

    public CadastroScreen() {
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
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(cadastrarButton, gbc);

        gbc.gridy = 5;
        backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(backButton, gbc);
    }

    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return new String(passField.getPassword());
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void addCadastrarListener(ActionListener listener) {
        cadastrarButton.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

}
