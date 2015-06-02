package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sala implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Usuario> usuarios;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuarioPrincipal;
    @ManyToMany
    private List<Documento> documentos;
    private String titulo;
    @OneToMany(mappedBy = "salaDoTopico" ,fetch=FetchType.EAGER)
    private List<Topico> topicos;
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    private String descricao;
    

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioPrincipal() {
        return usuarioPrincipal;
    }

    public void setUsuarioPrincipal(Usuario usuarioPrincipal) {
        this.usuarioPrincipal = usuarioPrincipal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Nome: "+this.titulo+" - Descrição: "+this.descricao+"\n";
    }
}
