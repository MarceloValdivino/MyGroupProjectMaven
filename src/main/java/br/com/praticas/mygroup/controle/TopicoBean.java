package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.controller.util.FacesUtils;
import br.com.praticas.mygroup.modelo.entidades.Sala;
import br.com.praticas.mygroup.modelo.entidades.Topico;
import br.com.praticas.mygroup.util.Facade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TopicoBean implements Serializable{

    private Topico topico = new Topico();
    private Sala sala;
    private List<Topico> topicos;
    private transient Facade facade;

    @PostConstruct
    public void init() {
        facade = new Facade();
        System.out.println("init() da topicoBean foi chamado.");
        System.out.println("Topico: " + topico.getTitulo());

    }

    public String visualizarPostagens(Long topicoId) {
        System.out.println("topicoId:" + topicoId);
        return "postagens.xhtml?faces-redirect=true&topicoId=" + topicoId;
    }

    public String salvar() {
        System.out.println("Entrei no salvar Topico.");
        topico.setDataDeCriacao(new Date());
        topico.setSalaTopico(sala);
        if (sala != null) {
            try {
                facade.salvarTopico(topico);
                FacesUtils.showMessageDialog("Sucesso!", "Tópico criado com sucesso: ");
                return "/minha-sala.xhtml?faces?redirect=true";
            } catch (Exception ex) {
                FacesUtils.showMessageDialog("Erro", "Erro ao tentar criar tópico.");
                return null;
            }
        } else {
            FacesUtils.showMessageDialog("Erro", "Não foi possivél encontrar uma sala.");
            return null;
        }
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public List<Topico> getTopicos() {
        try {
            return facade.buscarTopicos(sala);
        } catch (Exception ex) {
            Logger.getLogger(TopicoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
