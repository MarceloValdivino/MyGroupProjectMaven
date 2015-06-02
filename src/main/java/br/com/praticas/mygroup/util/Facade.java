package br.com.praticas.mygroup.util;

import br.com.praticas.mygroup.modelo.entidades.Mensagem;
import br.com.praticas.mygroup.modelo.entidades.Postagem;
import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Topico;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.modelo.repositorios.IMensagemDao;
import br.com.praticas.mygroup.modelo.repositorios.IPostagemDao;
import br.com.praticas.mygroup.modelo.repositorios.ISalaDao;
import br.com.praticas.mygroup.modelo.repositorios.ITopicoDao;
import br.com.praticas.mygroup.modelo.repositorios.IUsuarioDao;
import java.util.List;

public class Facade {

    private IUsuarioDao daoUsuario;
    private ISalaDao daoSala;
    private ITopicoDao daoTopico;
    private IMensagemDao daoMensagem;
    private IPostagemDao daoPostagem;

    public Facade() {
        daoUsuario = FactoryDao.getUsuarioDao();
        daoSala = FactoryDao.getSalaDao();
        daoTopico = FactoryDao.getTopicoDao();
        daoMensagem = FactoryDao.getMensagemDao();
        daoPostagem = FactoryDao.getPostagemDao();
    }

    public void salvarUsuario(Usuario u) throws Exception {
        try {
            u.setAtivo(true);
            daoUsuario.salvar(u);
        } catch (DaoException ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    public Usuario buscarUsuario(Long id) throws Exception {
        try {
            return daoUsuario.buscar(id);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Usuario buscarUsuarioPorLogin(String login) throws Exception {
        try {
            return daoUsuario.buscarUsuarioPorLogin(login);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) throws Exception {
        try {
            return daoUsuario.buscarUsuarioPorNome(nome);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void editarUsuario(Usuario u) throws Exception {
        try {
            daoUsuario.editar(u);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void removerUsuario(Usuario u) throws Exception {
        try {
            daoUsuario.deletar(u.getId());
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void removerUsuario(Long id) throws Exception {
        try {
            daoUsuario.deletar(id);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() throws Exception {
        try {
            return daoUsuario.listarTodos();
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Usuario logar(String login, String senha) throws Exception {
        try {
            return daoUsuario.logar(login, senha);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void salvarSala(Sala s) throws Exception {
        try {
            daoSala.salvar(s);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Sala buscarSala(Long id) throws Exception {
        try {
            return daoSala.buscar(id);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Sala> buscarSalasPorTitulo(String titulo) throws Exception {
        try {
            return daoSala.buscarSalasPorTitulo(titulo);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Sala> buscarSalasParticipando(Usuario u) throws Exception {
        try {
            return daoSala.buscarSalasParticipando(u.getId());
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void editarSala(Sala s) throws Exception {
        try {
            daoSala.editar(s);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void removerSala(Long id) throws Exception {
        try {
            daoSala.deletar(id);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void removerSala(Sala s) throws Exception {
        try {
            daoSala.deletar(s.getId());
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Sala> listarSalas() throws Exception {
        try {
            return daoSala.listarTodos();
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Usuario> listaUsuariosNaoSala(Sala s) throws Exception {
        try {
            return daoSala.buscarUsuariosNaoSala(s);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Sala> buscarMinhasSalas(Usuario u) throws Exception {
        try {
            return daoSala.buscarMinhasSalas(u);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Usuario> listaUsuariosNaoSala(Sala s, String nome) throws Exception {
        try {
            return daoSala.buscarUsuariosNaoSala(s, nome);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void enviarConvites(Mensagem msg, List<Usuario> usuarios) throws Exception {
        for (Usuario u : usuarios) {
            msg.setDestinatario(u);
            daoMensagem.salvar(msg);
        }
    }

    public void salvarTopico(Topico topico) throws Exception {
        try {
            daoTopico.salvar(topico);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void salvarMensagem(Mensagem m) throws Exception {
        try {
            daoMensagem.salvar(m);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void editarMensagem(Mensagem m) throws Exception {
        try {
            daoMensagem.editar(m);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void removerMensagem(Mensagem m) throws Exception {
        try {
            daoMensagem.deletar(m.getId());
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Usuario> buscarUsuariosLogin(String usuario) throws Exception {
        try {
            return daoUsuario.buscarUsuarioLogin(usuario);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void salvarPostagem(Postagem p) throws Exception {
        try {
            daoPostagem.salvar(p);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Topico> buscarTopicos(Sala s) throws Exception {
        try {
            return daoTopico.buscarTopicos(s.getId());
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public List<Postagem> buscarPostagens(Topico t) throws Exception {
        try {
            return daoPostagem.buscarPostagens(t.getId());
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public List<Postagem> buscarPostagens(Long idTopico) throws Exception {
        try {
            return daoPostagem.buscarPostagens(idTopico);
        } catch (DaoException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
