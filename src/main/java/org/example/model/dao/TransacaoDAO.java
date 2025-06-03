package org.example.model.dao;

import org.example.model.entity.Transacao;

import java.util.List;

public interface TransacaoDAO {

    void save(Transacao transacao);

    void update(Transacao transacao);

    void delete(Transacao transacao);

    List<Transacao> findAll();

    Transacao findById(Long id);

    void deleteById(Long id);

}
