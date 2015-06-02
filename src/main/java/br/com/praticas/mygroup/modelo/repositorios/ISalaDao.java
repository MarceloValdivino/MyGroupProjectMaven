package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;

public interface ISalaDao extends GenericDao<Sala> {

    public List<Sala> buscarSalasPorTitulo(String titulo) throws DaoException;

    public List<Usuario> buscarUsuariosNaoSala(Sala s) throws DaoException;

    public List<Usuario> buscarUsuariosNaoSala(Sala s, String nome) throws DaoException;

    public List<Sala> buscarMinhasSalas(Usuario u) throws DaoException;

    public List<Sala> buscarSalasParticipando(Long id) throws DaoException;
}
