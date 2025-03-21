package org.example;

import javax.swing.*;
import java.awt.*;

public class CadastroScreen extends JFrame {

    public CadastroScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 200);
        setTitle("Cadastro");
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(4, 4, 5, 5);
        panel.setLayout(layout);
        JLabel lblNome = new JLabel("Nome");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblTelefone = new JLabel("Telefone");
        panel.add(lblNome);
        panel.add(lblEmail);
        panel.add(lblTelefone);
        add(panel);

    }

}
