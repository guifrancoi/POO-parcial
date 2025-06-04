package org.example.model.dao;

import org.example.model.entity.Transacao;
import org.example.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO {

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public void salvar(Transacao transacao) {
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(transacao);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void atualizarTransacao(Transacao transacao) {
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.merge(transacao);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
            }
        }
    }

    @Override
    public Transacao buscaPorIdEUsuario(Long idTransacao, Long idUsuario) {
        try(Session session = factory.openSession()) {
            Query<Transacao> query = session.createQuery(
                    "FROM Transacao t WHERE t.id = :idTransacao AND t.usuario.id = :idUsuario", Transacao.class);
            query.setParameter("idTransacao", idTransacao);
            query.setParameter("idUsuario", idUsuario);

            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void excluirPorId(Long id) {
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Transacao transacao = session.get(Transacao.class, id);
                if (transacao != null) {
                    session.remove(transacao);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Transacao> buscaTransacoesPorUsuario(Usuario usuario) {
        System.out.println("dentro do findByUsuario");
        try (Session session = factory.openSession()) {
            Query<Transacao> query = session.createQuery("FROM Transacao WHERE usuario = :usuario", Transacao.class);
            query.setParameter("usuario", usuario);
            return query.list();
        }
    }

    @Override
    public List<Transacao> buscaTransacoesPorFiltro(Usuario usuario, String data, String categoria, String tipo) {
        try (Session session = factory.openSession()) {
            StringBuilder hql = new StringBuilder("FROM Transacao t WHERE t.usuario = :usuario");

            if (data != null && !data.isBlank() && !data.contains("_")) {
                hql.append(" AND t.data = :data");
            }
            if (categoria != null && !categoria.isBlank()) {
                hql.append(" AND t.categoria = :categoria");
            }
            if (tipo != null && !tipo.isBlank()) {
                hql.append(" AND t.tipo = :tipo");
            }

            Query<Transacao> query = session.createQuery(hql.toString(), Transacao.class);
            query.setParameter("usuario", usuario);

            if (data != null && !data.isBlank() && !data.contains("_")) {
                query.setParameter("data", LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            if (categoria != null && !categoria.isBlank()) {
                query.setParameter("categoria", categoria);
            }
            if (tipo != null && !tipo.isBlank()) {
                query.setParameter("tipo", tipo);
            }

            return query.list();
        }
    }

}
