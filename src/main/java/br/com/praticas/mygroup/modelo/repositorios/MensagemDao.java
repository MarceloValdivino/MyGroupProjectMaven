package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.modelo.entidades.Mensagem;
import br.com.praticas.mygroup.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;

public class MensagemDao implements IMensagemDao {
    @Override
    public void salvar(Mensagem msg) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(msg);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Mensagem m = em.getReference(Mensagem.class, id);
        if (m != null) {
            em.remove(m);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(Mensagem t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Mensagem buscar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Mensagem m = em.find(Mensagem.class, id);

        em.close();
        return m;
    }

    @Override
    public List<Mensagem> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Mensagem> postagem = em.createQuery("SELECT m FROM Mensagem m", Mensagem.class).getResultList();
        em.close();
        return postagem;
    }
}
