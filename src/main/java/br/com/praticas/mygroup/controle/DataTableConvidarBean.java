package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.modelo.entidades.Mensagem;
import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.Facade;
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

@ViewScoped
@ManagedBean
public class DataTableConvidarBean extends Beans {

    private List<Usuario> alunosSelecionados;
    private List<Usuario> alunosEncontrados;
    private List<Usuario> alunosFiltrados;
    private Sala sala;
    private String nome;
    private Facade facade;

    @PostConstruct
    public void init() {
        facade = new Facade();
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ex = fc.getExternalContext();
        HttpSession session = (HttpSession) ex.getSession(false);
        this.sala = (Sala) session.getAttribute("sala");
    }

    public List<Usuario> getAlunosFiltrados() {
        return alunosFiltrados;
    }

    public void setAlunosFiltrados(List<Usuario> alunosFiltrados) {
        this.alunosFiltrados = alunosFiltrados;
    }

    public List<Usuario> getAlunosEncontrados() {
        return alunosEncontrados;
    }

    public void setAlunosEncontrados(List<Usuario> alunosEncontrados) {
        this.alunosEncontrados = alunosEncontrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void buscarAlunos() {
        try {
            System.out.println("Fazendo busca...");
            alunosEncontrados = facade.listaUsuariosNaoSala(sala, nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            showMessageDialog("Erro", "Ocorreu um erro ao buscar alunos.");
        }
    }

    public void enviarConvites() {
        try {
            Mensagem msg = new Mensagem();
            msg.setSala(sala.getId());
            Sala s = facade.buscarSala(sala.getId());
            Usuario u = s.getUsuarioPrincipal();
            msg.setRemetente(u);
            msg.setDiaDoEnvio(new Date());
            msg.setConvite(true);
            msg.setMensagem("Você foi convidado para participar da Sala: " + sala.getTitulo() + " criada por " + u.getNome() + ".\nDeseja Participar?");
            facade.enviarConvites(msg, alunosSelecionados);
            showMessageDialog("Sucesso", "Os convites foram enviados com sucesso.");
        } catch (Exception ex) {
            showMessageDialog("Erro", "Erro ao tentar enviar o convite.");
            Logger.getLogger(DataTableConvidarBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> getAlunosSelecionados() {
        return alunosSelecionados;
    }

    public void setAlunosSelecionados(List<Usuario> alunosSelecionados) {
        this.alunosSelecionados = alunosSelecionados;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
