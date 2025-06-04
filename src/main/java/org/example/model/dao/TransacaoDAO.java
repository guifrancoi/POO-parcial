package org.example.model.dao;

import org.example.model.entity.Transacao;
import org.example.model.entity.Usuario;

import java.util.List;

public interface TransacaoDAO {

    void salvar(Transacao transacao);

    void atualizarTransacao(Transacao transacao);

    Transacao buscaPorIdEUsuario(Long idTransacao, Long idUsuario);

    void excluirPorId(Long id);

    List<Transacao> buscaTransacoesPorUsuario(Usuario usuario);

    List<Transacao> buscaTransacoesPorFiltro(Usuario usuario, String data, String categoria, String tipo);

}
