package org.example.controller;

import org.example.NavigationFrame;
import org.example.model.dao.UsuarioDAO;
import org.example.model.dao.UsuarioDAOImpl;
import org.example.model.entity.Usuario;
import org.example.view.CadastroScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroController {

    private final NavigationFrame navigationFrame;
    private final CadastroScreen cadastroScreen;
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public CadastroController(NavigationFrame navigationFrame, CadastroScreen view) {
        this.navigationFrame = navigationFrame;
        cadastroScreen = view;
        cadastroScreen.addCadastrarListener(new CadastroAction());
        cadastroScreen.addLoginListener(e -> navigationFrame.showScreen("Login"));
    }

    private class CadastroAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String usuario = cadastroScreen.getUsername();
            String password = cadastroScreen.getPassword();

            boolean sucesso = cadastrarUsuario(usuario, password);

            if (sucesso) {
                cadastroScreen.showMessage("Usuário cadastrado com sucesso!");
                navigationFrame.showScreen("Login");
            } else {
                cadastroScreen.showError("Erro ao cadastrar. Verifique se o usuário já existe ou se os campos estão vazios");
            }
        }
    }


    private boolean cadastrarUsuario(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return false;
        }

        if (usuarioDAO.existeUsuario(username)) {
            return false;
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);

        usuarioDAO.salvar(usuario);
        return true;
    }
}
