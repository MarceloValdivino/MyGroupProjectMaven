package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Topico;
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
public class SalaBean extends Beans {

    private Sala sala = new Sala();
    private List<Sala> salasParticipando;
    private List<Sala> minhasSalas;
    private Sala salaSelecionada;
    private Facade facade;
    private Usuario usuario;

    public List<Sala> getSalasParticipando() {
        return salasParticipando;
    }

    public void setSalasParticipando(List<Sala> salasParticipando) {
        this.salasParticipando = salasParticipando;
    }

    public List<Sala> getMinhasSalas() {
        return minhasSalas;
    }

    public void setMinhasSalas(List<Sala> minhasSalas) {
        this.minhasSalas = minhasSalas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        facade = new Facade();
        System.out.println("init() da salaBean foi chamado.");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        this.usuario = (Usuario) session.getAttribute("usuario");
        System.out.println("Usuário: " + usuario.getNome());
        Sala sl = (Sala) session.getAttribute("sala");
        if(sl==null){
            this.sala = new Sala();
        } else {
            this.sala = sl;
        }
        
        try {
            minhasSalas = facade.buscarMinhasSalas(usuario);
            salasParticipando = facade.buscarUsuario(usuario.getId()).getSalasParticipando();
        } catch (Exception ex) {
            System.out.println("Erro ao buscar minhasSalas.");
            Logger.getLogger(SalaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(minhasSalas == null)
            System.out.println("Minhas salas em null");
        else
            System.out.println("Minhas salas não estão em null");
        System.out.println("Número de salas: "+minhasSalas.size());
    }

    public String salvar() {
        sala.setUsuarioPrincipal(usuario);
        sala.setAtivo(true);
        try {
            facade.salvarSala(sala);
        } catch (Exception e) {
            showMessageDialog("Erro", "Erro ao tentar cadastrar nova sala.");
        }
        showMessageDialog("Cadastro", "Sala cadastrada com sucesso.<br/><br/>Nome: <b>" + this.sala.getTitulo() + "</b>");
        return "minha-sala.xhtml?faces-redirect=true";
    }

    public void mostrar(){
        for(Sala s : minhasSalas){
            System.out.println("Sala: "+s.getTitulo());
            for(Topico t : s.getTopicos()){
                System.out.println("Titulo: "+t.getTitulo());
            }
        }
        showMessageDialog("KCT", "Mostrar");
    }
    
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String visualizarSala() {
        if (salaSelecionada != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("sala", salaSelecionada);
            return "/sala.xhtml?faces-redirect=true";
        } else {
            showMessageDialog("Atenção", "Selecione uma sala.");
        }
        return null;
    }

    public Sala getSalaSelecionada() {
        return salaSelecionada;
    }

    public void setSalaSelecionada(Sala salaSelecionada) {
        this.salaSelecionada = salaSelecionada;
    }
}
