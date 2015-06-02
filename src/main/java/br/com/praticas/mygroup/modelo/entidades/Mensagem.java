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
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario remetente;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario destinatario;
    private String mensagem;
    private boolean convite;
    private Long sala;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date diaDoEnvio;
    
    public Date getDiaDoEnvio() {
        return diaDoEnvio;
    }

    public void setDiaDoEnvio(Date diaDoEnvio) {
        this.diaDoEnvio = diaDoEnvio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Long getSala() {
        return sala;
    }

    public void setSala(long sala) {
        this.sala = sala;
    }
    
    public boolean isConvite() {
        return convite;
    }

    public void setConvite(boolean convite) {
        this.convite = convite;
    }
}
