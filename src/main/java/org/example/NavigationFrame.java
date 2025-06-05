package org.example;

import org.example.controller.LoginController;
import org.example.controller.CadastroController;
import org.example.view.CadastroScreen;
import org.example.view.LoginScreen;

import javax.swing.*;
import java.awt.*;

public class NavigationFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public NavigationFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Sistema de Login");
        setLocationRelativeTo(null);

        // Criando o CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Instancia as views primeiro
        LoginScreen loginScreen = new LoginScreen();
        CadastroScreen cadastroScreen = new CadastroScreen();

        // Cria os controllers e registra eventos
        new LoginController(this, loginScreen);
        new CadastroController(this, cadastroScreen); // supondo que você siga o mesmo padrão para cadastro

        // Adiciona as telas ao painel principal
        mainPanel.add(loginScreen, "Login");
        mainPanel.add(cadastroScreen, "Cadastro");

        add(mainPanel);
        setVisible(true);
    }

    // Método para trocar entre as telas
    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }
}
