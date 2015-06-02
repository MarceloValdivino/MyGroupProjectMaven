package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.modelo.entidades.Postagem;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;

public interface IPostagemDao extends GenericDao<Postagem>{

    public List<Postagem> buscarPostagens(Long id) throws DaoException;
    
}
