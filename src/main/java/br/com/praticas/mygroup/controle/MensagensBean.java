package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.modelo.entidades.Mensagem;
import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.Facade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ViewScoped
@ManagedBean
public class MensagensBean extends Beans {

    private List<Mensagem> mensagensRecebidas;
    private List<Mensagem> mensagensEnviadas;
    private Mensagem mensagem;
    private Facade facade;

    @PostConstruct
    public void init() {
        facade = new Facade();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u != null) {
            try {
                u = facade.buscarUsuario(u.getId());
                this.mensagensEnviadas = u.getMensagensEnviadas();
                this.mensagensRecebidas = u.getMensagensRecebidas();
                for (Mensagem m : mensagensRecebidas) {
                    System.out.println("Mensagem: " + m.getMensagem());
                }
                for (Mensagem m : mensagensEnviadas) {
                    System.out.println("Mensagem: " +m.getId() + m.getMensagem());
                }
            } catch (Exception ex) {
                Logger.getLogger(MensagensBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String aceitarConvite() {
        try {
            mensagem.setConvite(false);
            Sala s = facade.buscarSala(mensagem.getSala());
            s.getUsuarios().add(mensagem.getDestinatario());
            facade.editarSala(s);
            facade.editarMensagem(mensagem);
            return "/home.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            showMessageDialog("Erro", "Ocorreu um erro ao aceitar o convite para entrar na sala.");
            Logger.getLogger(MensagensBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String apagarMensagem(){
        try {
            facade.removerMensagem(mensagem);
            showMessageDialog("Atenção", "Mensagem removida com sucesso.");
            return "/home.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            showMessageDialog("Erro", "Erro ao tentar remover mensagem.");
            Logger.getLogger(MensagensBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    public void setMensagensRecebidas(List<Mensagem> mensagensRecebidas) {
        this.mensagensRecebidas = mensagensRecebidas;
    }

    public List<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public void setMensagensEnviadas(List<Mensagem> mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
}
