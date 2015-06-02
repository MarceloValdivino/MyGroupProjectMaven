package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.Facade;
import br.com.praticas.mygroup.util.MensagensUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@SessionScoped
@ManagedBean
public class UsuarioBean extends Beans {

    private Usuario usuario = new Usuario();
    private String login;
    private String senha;
    private List<Usuario> usuarios;
    private Facade facade;

    @PostConstruct
    public void init() {
        facade = new Facade();
    }

    public String salvar() {
        try {
            if (usuario.getFotoPerfil() == null) {
                System.out.println("Foto do Usuário está em null.");
            } else {
                System.out.println("Foto do Usuário não está e null.");
            }
            facade.salvarUsuario(usuario);
            showMessageDialog("Cadastro", "Usuário salvo com sucesso.<br/><br/>Nome: <b>" + this.usuario.getNome() + "</b>");
            RequestContext.getCurrentInstance().update("@all");
            this.usuario = new Usuario();
            return "index.xhtml";
        } catch (Exception e) {
            showMessageDialog("Erro", "Erro ao tentar cadastrar um novo usuário.<br></br>Verifique se o login ou a senha já não estão em uso.");
            return "index.xhtml";
        }
    }

    public String login() {
        System.out.println("login()");
        if (autenticar()) {
            return "home.xhtml?faces-redirect=true";
        } else {
            showMessageDialog("Login", "Usuário ou senha inválido.");
            return null;
        }
    }

    public boolean autenticar() {
        try {
            this.usuario = facade.logar(login, senha);
            if (usuario == null) {
                showMessageDialog("Atenção", "Usuário ou senha inválidos.");
                return false;
            }
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("usuario", usuario);
            return true;
        } catch (Exception e) {
            showMessageDialog("Erro", "Erro ao tentar logar.");
            return false;
        }
    }

    public String registraSaida() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.removeAttribute("usuario");
        session.removeAttribute("sala");
        this.usuario = new Usuario();
        System.out.println("Usuário: " + this.usuario.getNome());
        return "index";
    }

    public List<Usuario> getUsuarios() throws Exception {
        if (usuarios == null) {
            usuarios = facade.listarUsuarios();
        }
        return usuarios;
    }

    public void uploadImage(FileUploadEvent event) {
        try {

            byte[] arquivo = event.getFile().getContents();
            System.out.println("byte: " + arquivo.toString());
            this.usuario.setFotoPerfil(arquivo);

        } catch (Exception ex) {
            System.out.println("Erro no upload de imagem" + ex);
        }
    }

    public String salvarEdicoes() {
        try {
            facade.editarUsuario(usuario);
            return "/home.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            showMessageDialog("Erro", MensagensUtil.getValor(MensagensUtil.MSG_ERRO_EDITAR));
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String apagarUsuario() {
        try {
            usuario.setAtivo(false);
            usuario.setNome(usuario.getId().toString());
            usuario.setEmail(usuario.getId().toString());
            facade.editarUsuario(usuario);
            
            return registraSaida();
        } catch (Exception ex) {
            showMessageDialog("Erro", MensagensUtil.getValor(MensagensUtil.MSG_ERRO_REMOVER));
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
