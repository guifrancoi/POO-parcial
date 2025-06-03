package org.example.model.dao;

import org.example.model.entity.Transacao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Falha ao inicializar o Hibernate");
        }
    }

    @Override
    public void save(Transacao transacao) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(transacao);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Transacao transacao) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(transacao);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Transacao transacao) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(session.contains(transacao) ? transacao : session.merge(transacao));
        tx.commit();
        session.close();
    }

    @Override
    public List<Transacao> findAll() {
        Session session = sessionFactory.openSession();
        List<Transacao> transacaos = session.createQuery("FROM Transacao", Transacao.class).list();
        session.close();
        return transacaos;
    }

    @Override
    public Transacao findById(Long id) {
        Session session = sessionFactory.openSession();
        Transacao transacao = session.get(Transacao.class, id);
        session.close();
        return transacao;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Transacao transacao = session.get(Transacao.class, id);
        if (transacao != null) {
            session.remove(transacao);
        }
        tx.commit();
        session.close();
    }
}
