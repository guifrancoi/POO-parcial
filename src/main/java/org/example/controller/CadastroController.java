package org.example.controller;

import org.example.model.dao.UsuarioDAO;
import org.example.model.dao.UsuarioDAOImpl;
import org.example.model.entity.Usuario;

public class CadastroController {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();


    public boolean cadastrarUsuario(String username, String password) {
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
