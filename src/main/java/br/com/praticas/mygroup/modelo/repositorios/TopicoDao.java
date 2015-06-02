package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.util.JPAUtil;
import br.com.praticas.mygroup.modelo.entidades.Topico;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TopicoDao implements ITopicoDao{

    @Override
    public void salvar(Topico t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        
        em.persist(t);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Topico t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Topico buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Topico> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Topico> topicos = em.createQuery("SELECT t FROM Topico t", Topico.class).getResultList();
        em.close();
        return topicos;
    }

    @Override
    public List<Topico> buscarTopicos(Long idSala) throws DaoException {
        List<Topico> topicosSala;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT t FROM Topico t WHERE t.salaDoTopico = :idSala", Topico.class);
        query.setParameter("idSala", idSala);
        topicosSala = query.getResultList();
        em.close();
        return topicosSala;
    }
    
}
