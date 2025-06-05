package org.example.controller;

import org.example.NavigationFrame;
import org.example.model.dao.UsuarioDAO;
import org.example.model.dao.UsuarioDAOImpl;
import org.example.model.entity.Usuario;
import org.example.view.LoginScreen;
import org.example.view.MainScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private final NavigationFrame navigationFrame;
    private final LoginScreen loginScreen;
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public LoginController(NavigationFrame navigationFrame, LoginScreen view) {
        this.navigationFrame = navigationFrame;
        loginScreen = view;
        loginScreen.addLoginListener(new LoginAction());
        loginScreen.addCadastrarListener(e -> navigationFrame.showScreen("Cadastro"));
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginScreen.getUsername();
            String senha = loginScreen.getPassword();

            Usuario usuario = usuarioDAO.buscarPorNomeESenha(username, senha);

            if (usuario != null) {
                loginScreen.showMessage("Login bem-sucedido!");
                MainScreen mainScreen = new MainScreen(usuario);
                new MainScreenController(mainScreen, usuario);
                mainScreen.setVisible(true);
                navigationFrame.setVisible(false);
                navigationFrame.dispose();
            } else {
                loginScreen.showError("Usuário ou senha inválidos!");
            }
        }
    }


}
