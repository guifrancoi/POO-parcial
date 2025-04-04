package org.example;

import org.example.view.CadastroScreen;
import org.example.view.LoginScreen;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Sistema de Login");
        setLocationRelativeTo(null);

        // Criando o CardLayout para trocar as telas
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adicionando as telas ao painel principal
        mainPanel.add(new LoginScreen(this), "Login");
        mainPanel.add(new CadastroScreen(this), "Cadastro");

        add(mainPanel);
        setVisible(true);
    }

    // Método para trocar entre as telas
    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }

}
