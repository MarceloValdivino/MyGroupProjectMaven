package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Topico implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private String titulo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeCriacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeFechamento;
    @OneToMany(mappedBy = "topico")
    private List<Postagem> postagens;
    @ManyToMany
    private List<Exercicio> exercicios;
    @ManyToOne(fetch = FetchType.EAGER)
    private Sala salaDoTopico;

    public Sala getSalaTopico() {
        return salaDoTopico;
    }

    public void setSalaTopico(Sala salaTopico) {
        this.salaDoTopico = salaTopico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Date getDataDeFechamento() {
        return dataDeFechamento;
    }

    public void setDataDeFechamento(Date dataDeFechamento) {
        this.dataDeFechamento = dataDeFechamento;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

     public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    @Override
    public String toString() {
        return "Titulo: "+this.titulo+" - Criado em: "+this.dataDeCriacao.toString()+"\n";
    }
    
    
}
