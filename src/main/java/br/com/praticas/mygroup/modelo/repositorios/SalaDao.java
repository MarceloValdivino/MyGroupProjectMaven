package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.util.JPAUtil;
import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SalaDao implements ISalaDao {

    @Override
    public void salvar(Sala t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(t);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Sala s = em.getReference(Sala.class, id);
        if (s != null) {
            em.remove(s);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(Sala t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Sala buscar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Sala sala = em.find(Sala.class, id);

        em.close();
        return sala;
    }

    @Override
    public List<Sala> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Sala> salas = em.createQuery("SELECT s FROM Sala s WHERE s.ativo = 1", Sala.class).getResultList();
        em.close();
        return salas;
    }

    @Override
    public List<Sala> buscarSalasPorTitulo(String titulo) {
        List<Sala> salas;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT s FROM Sala s WHERE s.titulo LIKE :titulo AND s.ativo = 1", Sala.class);
        query.setParameter("titulo", "%" + titulo + "%");
        salas = query.getResultList();
        em.close();
        return salas;
    }

    @Override
    public List<Usuario> buscarUsuariosNaoSala(Sala s) throws DaoException {
        List<Usuario> users;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("SELECT u.* FROM usuario u WHERE u.ativo = 1 AND u.id NOT IN(SELECT s.usuarios_id FROM sala_usuario s WHERE u.id = s.usuarios_id AND s.salasParticipando_id = :idSala)", Usuario.class);
        query.setParameter("idSala", s.getId());
        users = query.getResultList();
        em.close();
        return users;
    }

    @Override
    public List<Sala> buscarMinhasSalas(Usuario usuario) throws DaoException {
        List<Sala> salas;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT s FROM Sala s WHERE s.usuarioPrincipal.id = :id AND s.ativo = 1", Sala.class);
        query.setParameter("id", usuario.getId());
        salas = query.getResultList();
        em.close();
        return salas;
    }

    @Override
    public List<Usuario> buscarUsuariosNaoSala(Sala s, String nome) throws DaoException {
        List<Usuario> users;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("SELECT u.* FROM usuario u WHERE u.nome LIKE :nome AND u.id NOT IN(SELECT s.usuarios_id FROM sala_usuario s WHERE u.id = s.usuarios_id AND s.salasParticipando_id = :idSala)  AND u.id NOT IN(SELECT u.id FROM Sala sala WHERE u.id = sala.usuarioPrincipal_id AND sala.id = :idSala)", Usuario.class);
        query.setParameter("idSala", s.getId());
        query.setParameter("nome", "%" + nome + "%");
        users = query.getResultList();
        em.close();
        return users;
    }

    @Override
    public List<Sala> buscarSalasParticipando(Long id) throws DaoException {
        List<Sala> salasParticipando;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createNativeQuery("SELECT s.* FROM sala s, sala_usuario su WHERE su.salasParticipando = s.id AND su.usuarios_id = :id", Sala.class);
        query.setParameter("id", id);
        salasParticipando = query.getResultList();
        em.close();
        return salasParticipando;
    }

}
