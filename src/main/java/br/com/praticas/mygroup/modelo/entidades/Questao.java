package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Questao implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    @Lob
    private String descricao;
    private String[] alternativas;
    private int alternativaCorreta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public int getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(int alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }
}
