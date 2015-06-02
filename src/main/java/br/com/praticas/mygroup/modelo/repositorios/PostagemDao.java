package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.util.JPAUtil;
import br.com.praticas.mygroup.modelo.entidades.Postagem;
import br.com.praticas.mygroup.modelo.entidades.Topico;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PostagemDao implements IPostagemDao {

    @Override
    public void salvar(Postagem postagem) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(postagem);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Postagem p = em.getReference(Postagem.class, id);
        if (p != null) {
            em.remove(p);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(Postagem t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Postagem buscar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Postagem p = em.find(Postagem.class, id);

        em.close();
        return p;
    }

    @Override
    public List<Postagem> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Postagem> postagem = em.createQuery("SELECT p FROM Postagem p", Postagem.class).getResultList();
        em.close();
        return postagem;
    }

    @Override
    public List<Postagem> buscarPostagens(Long idTopico) throws DaoException {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT p FROM Postagem p WHERE p.topico.id =  :idTop", Postagem.class);
        query.setParameter("idTop", idTopico);
        List<Postagem> postagens = query.getResultList();
        em.close();
        return postagens;
    }

}
