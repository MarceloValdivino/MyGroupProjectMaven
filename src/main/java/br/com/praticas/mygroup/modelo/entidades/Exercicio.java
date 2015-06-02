package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

@Entity
public class Exercicio implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Questao> listaDeQuestoes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeCriacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeVencimento;

    public List<Questao> getListaDeQuestoes() {
        return listaDeQuestoes;
    }

    public void setListaDeQuestoes(List<Questao> listaDeQuestoes) {
        this.listaDeQuestoes = listaDeQuestoes;
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

    public Date getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(Date dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }
}
