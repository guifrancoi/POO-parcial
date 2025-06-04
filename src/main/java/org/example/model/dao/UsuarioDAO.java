package org.example.model.dao;

import org.example.model.entity.Usuario;

public interface UsuarioDAO {

    void salvar(Usuario usuario);

    Usuario buscarPorNomeESenha(String nome, String senha);

    boolean existeUsuario(String nome);
}
