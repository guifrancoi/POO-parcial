package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginScreen extends JFrame {

    JTextField userField = new JTextField(15);
    JPasswordField passField = new JPasswordField(15);

    // Simulação de um banco de dados (usuário e senha)
    private HashMap<String, String> userDatabase = new HashMap<>();

    public LoginScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Login");
        setLocationRelativeTo(null);

        // Definir GridBagLayout para o painel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        gbc.anchor = GridBagConstraints.CENTER; // Centralizar os componentes
        gbc.gridx = 0; // Coluna fixa

        // Fonte personalizada
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Label Usuário
        gbc.gridy = 0; // Primeira linha
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setFont(labelFont);
        panel.add(userLabel, gbc);

        // Campo Usuário
        gbc.gridy = 1; // Segunda linha
        userField.setFont(fieldFont);
        panel.add(userField, gbc);

        // Label Senha
        gbc.gridy = 2; // Terceira linha
        JLabel passLabel = new JLabel("Senha:");
        passLabel.setFont(labelFont);
        panel.add(passLabel, gbc);

        // Campo Senha
        gbc.gridy = 3; // Quarta linha
        passField.setFont(fieldFont);
        panel.add(passField, gbc);

        // Botão de Login
        gbc.gridy = 4; // Quinta linha
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(loginButton, gbc);

        // Adicionar evento ao botão
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });

        // Adicionar painel ao frame
        add(panel);
        setVisible(true);
    }

    // Método para validar o login
    private void validarLogin() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//            abrirTelaPrincipal();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir a nova "página" após o login bem-sucedido
//    private void abrirTelaPrincipal() {
//        dispose(); // Fecha a tela de login
//        new MainScreen(); // Abre a tela principal
//    }

}
