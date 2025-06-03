package org.example.controller;

import org.example.model.dao.TransacaoDAO;
import org.example.model.dao.TransacaoDAOImpl;
import org.example.model.entity.Transacao;

import java.util.List;

public class MainScreenController {

    private final TransacaoDAO transacaoDAO;

    public MainScreenController() {
        this.transacaoDAO = new TransacaoDAOImpl();
    }

    public List<Transacao> buscarTodasTransacoes() {
        return transacaoDAO.findAll();
    }

    public void salvarTransacao(Transacao transacao) {
        transacaoDAO.save(transacao);
    }

    public void atualizarTransacao(Transacao transacao) {
        transacaoDAO.update(transacao);
    }

    public void excluirTransacaoPorId(Long id) {
        transacaoDAO.deleteById(id);
    }

    public Transacao buscarPorId(Long id) {
        return transacaoDAO.findById(id);
    }
}
