package org.example.controller;

import org.example.model.dao.UsuarioDAO;
import org.example.model.dao.UsuarioDAOImpl;
import org.example.model.entity.Usuario;

public class LoginController {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public LoginController() {

    }

    public Usuario autenticar(String username, String senha) {
        if (username == null || senha == null || username.isBlank() || senha.isBlank()) {
            return null;
        }
        return usuarioDAO.buscarPorNomeESenha(username.trim(), senha);
    }
}
