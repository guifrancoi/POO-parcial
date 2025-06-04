package org.example.model.dao;

import org.example.model.entity.Transacao;
import org.example.model.entity.Usuario;

import java.util.List;

public interface TransacaoDAO {

    void save(Transacao transacao);

    void update(Transacao transacao);

    void delete(Transacao transacao);

    List<Transacao> findAll();

    Transacao findById(Long id);

    void deleteById(Long id);

    List<Transacao> findByUsuario(Usuario usuario);

    List<Transacao> buscarPorFiltros(Usuario usuario, String data, String categoria, String tipo);

}
