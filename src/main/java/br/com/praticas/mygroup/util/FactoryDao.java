package br.com.praticas.mygroup.util;

import br.com.praticas.mygroup.modelo.repositorios.IMensagemDao;
import br.com.praticas.mygroup.modelo.repositorios.IPostagemDao;
import br.com.praticas.mygroup.modelo.repositorios.ISalaDao;
import br.com.praticas.mygroup.modelo.repositorios.ITopicoDao;
import br.com.praticas.mygroup.modelo.repositorios.IUsuarioDao;
import br.com.praticas.mygroup.modelo.repositorios.MensagemDao;
import br.com.praticas.mygroup.modelo.repositorios.PostagemDao;
import br.com.praticas.mygroup.modelo.repositorios.SalaDao;
import br.com.praticas.mygroup.modelo.repositorios.TopicoDao;
import br.com.praticas.mygroup.modelo.repositorios.UsuarioDao;

public class FactoryDao {
        
    public static IUsuarioDao getUsuarioDao(){
        return new UsuarioDao();
    }
    public static ISalaDao getSalaDao(){
        return new SalaDao();
    }
    public static ITopicoDao getTopicoDao(){
        return new TopicoDao();
    }
    public static IMensagemDao getMensagemDao(){
        return new MensagemDao();
    }
    
    public static IPostagemDao getPostagemDao(){
        return new PostagemDao();
    }
}
