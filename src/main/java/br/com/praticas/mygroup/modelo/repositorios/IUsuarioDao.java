package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;

public interface IUsuarioDao extends GenericDao<Usuario>{
    public List<Usuario> buscarUsuarioPorNome(String nome) throws DaoException;
    public Usuario logar(String login, String senha) throws DaoException;
    public List<Usuario> buscarUsuarioLogin(String login) throws DaoException;
    public Usuario buscarUsuarioPorLogin(String login) throws DaoException;
}
