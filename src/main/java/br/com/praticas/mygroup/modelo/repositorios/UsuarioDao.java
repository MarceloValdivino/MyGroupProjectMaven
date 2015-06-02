package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.util.JPAUtil;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioDao implements IUsuarioDao {

    @Override
    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        usuario.setFotoPerfil(null);
        usuario.setDocumentoUpgrades(null);
        usuario.setPostagens(null);
        usuario.setSalasParticipando(null);
        System.out.println("Usuário: " + usuario);
        em.persist(usuario);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Usuario u = em.getReference(Usuario.class, id);
        if (u != null) {
            em.remove(u);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.merge(usuario);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Usuario buscar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Usuario usuario = em.find(Usuario.class, id);

        em.close();
        return usuario;
    }

    @Override
    public List<Usuario> buscarUsuarioPorNome(String nome) {
        List<Usuario> users;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE :nome AND u.ativo = 1", Usuario.class);
        query.setParameter("nome", "%" + nome + "%");
        users = query.getResultList();
        em.close();
        return users;
    }

    @Override
    public List<Usuario> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u WHERE u.ativo = 1", Usuario.class).getResultList();
        em.close();
        return usuarios;
    }

    @Override
    public Usuario logar(String login, String senha) throws DaoException {

        EntityManager em = JPAUtil.getEntityManager();
        Usuario u;
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha AND u.ativo = 1", Usuario.class);
        query.setParameter("login", login);
        query.setParameter("senha", senha);

        u = (Usuario) query.getSingleResult();
        em.close();
        return u;
    }

    @Override
    public List<Usuario> buscarUsuarioLogin(String login) throws DaoException {
        EntityManager em = JPAUtil.getEntityManager();
        List<Usuario> users;
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login LIKE :login AND u.ativo = 1", Usuario.class);
        query.setParameter("login", "%"+login+"%");
        users = query.getResultList();
        em.close();
        return users;
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String login) throws DaoException {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.ativo = 1", Usuario.class);
        query.setParameter("login", login);
        Usuario u = (Usuario)query.getSingleResult();
        em.close();
        return u;
    }

}
