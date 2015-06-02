package br.com.praticas.mygroup.controle;

import br.com.praticas.mygroup.controller.util.FacesUtils;
import br.com.praticas.mygroup.modelo.entidades.Postagem;
import br.com.praticas.mygroup.modelo.entidades.Topico;
import br.com.praticas.mygroup.modelo.entidades.Usuario;
import br.com.praticas.mygroup.util.Facade;
import br.com.praticas.mygroup.util.MensagensUtil;
import java.io.Serializable;
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
public class PostagemBean implements Serializable {

    private Postagem postagem = new Postagem();
    private Topico topico;
    private List<Postagem> postagens;
    private transient Facade facade;

    @PostConstruct
    public void init() {
        facade = new Facade();

        String idTopico = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("topicoId");

        if (idTopico != null) {
            try {
                postagens = facade.buscarPostagens(Long.parseLong(idTopico));
            } catch (Exception ex1) {
                Logger.getLogger(PostagemBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void fazerPostagem() {
        if (topico != null) {
            try {
                postagem.setDataDePostagem(new Date());
                postagem.setTopico(topico);
                postagem.setUsuarioPost(getUsuario());
                facade.salvarPostagem(postagem);
                postagem = new Postagem();
                FacesUtils.showMessageDialog("Sucesso!", MensagensUtil.getValor(MensagensUtil.MSG_SUCESSO_SALVAR));
            } catch (Exception ex) {
                FacesUtils.showMessageDialog("Erro", MensagensUtil.getValor(MensagensUtil.MSG_ERRO_SALVAR));
                Logger.getLogger(PostagemBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesUtils.showMessageDialog("Erro", "Não foi possível encontrar o tópico.");
        }
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    private Usuario getUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        return (Usuario) session.getAttribute("usuario");
    }
}
