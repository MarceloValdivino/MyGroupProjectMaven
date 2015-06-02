package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.modelo.entidades.Mensagem;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.Facade;
import br.com.praticas.mygroup.util.MensagensUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class EnviarMensagemBean extends Beans {

    private List<Usuario> todosOsUsuarios;
    private List<String> logins;
    private Facade facade;
    private String msg;
    private Usuario remetente;
    private Mensagem mensagem;

    @PostConstruct
    public void init() {
        this.facade = new Facade();
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) ex.getSession(false);
        remetente = (Usuario) session.getAttribute("usuario");
    }

    public void enviarMensagens() {
        try {
            for (String l : logins) {
                Usuario u = facade.buscarUsuarioPorLogin(l);
                Mensagem m = new Mensagem();
                m.setConvite(false);
                m.setDestinatario(u);
                m.setRemetente(remetente);
                System.out.println("Mensagem: " + getMsg());
                m.setMensagem(getMsg());
                m.setDiaDoEnvio(new Date());
                facade.salvarMensagem(m);
            }
            showDialog("ok");
        } catch (Exception e) {
            e.printStackTrace();
            showMessageDialog("Erro", MensagensUtil.getValor(MensagensUtil.MSG_ERRO_SALVAR));
        }
    }

    public List<String> listarUsuarios(String usuario) {
        try {
            List<Usuario> usuarios = facade.buscarUsuariosLogin(usuario);
            List<String> logins = new ArrayList<>();
            for (Usuario u : usuarios) {
                logins.add(u.getLogin());
            }
            for (String s : logins) {
                System.out.println("Login: " + s);
            }
            return logins;
        } catch (Exception ex) {
            ex.printStackTrace();
            showMessageDialog("Erro", MensagensUtil.getValor(MensagensUtil.MSG_ERRO));
            return null;
        }
    }
    
       public void responderMensagem() {
        if (mensagem != null) {
            try {
                Usuario user = mensagem.getDestinatario();
                Mensagem mensg = new Mensagem();
                mensg.setRemetente(remetente);
                mensg.setDestinatario(user);
                mensg.setMensagem(msg + "<br></br>Resposta de:" + mensagem.getDestinatario().getNome()
                        + "<br></br>Enviado em: " + mensagem.getDiaDoEnvio().toString());
                facade.salvarMensagem(mensg);
            } catch (Exception ex) {
                showMessageDialog("Erro", MensagensUtil.getValor(MensagensUtil.MSG_ERRO_SALVAR));
                Logger.getLogger(EnviarMensagemBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showMessageDialog("Erro", "Ocorreu um erro. Mensagem em null.");
        }
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public List<Usuario> getTodosOsUsuarios() {
        return this.todosOsUsuarios;
    }

    public void setTodosOsUsuarios(List<Usuario> todosOsUsuarios) {
        this.todosOsUsuarios = todosOsUsuarios;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
