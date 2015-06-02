package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.util.DaoException;
import java.util.List;

public interface GenericDao<T>{
    // Pesquisar sobre refletions
    
    public void salvar(T t) throws DaoException;
    public void deletar(Long id) throws DaoException;
    public void editar(T t) throws DaoException;
    public T buscar(Long id) throws DaoException;
    public List<T> listarTodos() throws DaoException;
    
}
