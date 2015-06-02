/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.praticas.mygroup.modelo.repositorios;

import br.com.praticas.mygroup.modelo.entidades.Topico;
import br.com.praticas.mygroup.util.DaoException;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public interface ITopicoDao extends GenericDao<Topico>{

    public List<Topico> buscarTopicos(Long idSala) throws DaoException;
    
}
