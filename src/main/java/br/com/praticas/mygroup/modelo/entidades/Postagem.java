package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Postagem implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDePostagem;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioPost;
    @ManyToOne(fetch = FetchType.EAGER)
    private Topico topico;
    private String postagem;

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDePostagem() {
        return dataDePostagem;
    }

    public void setDataDePostagem(Date dataDePostagem) {
        this.dataDePostagem = dataDePostagem;
    }

    public Usuario getUsuarioPost() {
        return usuarioPost;
    }

    public void setUsuarioPost(Usuario usuarioPost) {
        this.usuarioPost = usuarioPost;
    }

    public String getPostagem() {
        return postagem;
    }

    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }

    @Override
    public String toString() {
        return "Postagem: "+this.postagem+" - Data: "+this.dataDePostagem.toString()+" - Usuário: "+this.usuarioPost+"\n";
    }
    
    
}
