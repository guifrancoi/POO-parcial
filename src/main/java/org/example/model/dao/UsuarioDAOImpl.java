package org.example.model.dao;

import org.example.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public void salvar(Usuario usuario) {
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(usuario);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
            }
        }
    }

    @Override
    public Usuario buscarPorNomeESenha(String nome, String senha) {
        try (Session session = factory.openSession()) {
            Query<Usuario> query = session.createQuery("FROM Usuario WHERE username = :nome AND password = :senha", Usuario.class);
            query.setParameter("nome", nome);
            query.setParameter("senha", senha);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean existeUsuario(String nome) {
        try (Session session = factory.openSession()) {
            Query<Usuario> query = session.createQuery("FROM Usuario WHERE username = :nome", Usuario.class);
            query.setParameter("nome", nome);
            return query.uniqueResult() != null;
        }
    }

}
