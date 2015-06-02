package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Documento implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private Byte[] arquivo;
    @ManyToOne
    private Usuario usuarioUpgrade;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataUpgrade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(Byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Usuario getUsuarioUpgrade() {
        return usuarioUpgrade;
    }

    public void setUsuarioUpgrade(Usuario usuarioUpgrade) {
        this.usuarioUpgrade = usuarioUpgrade;
    }

    public Date getDataUpgrade() {
        return dataUpgrade;
    }

    public void setDataUpgrade(Date dataUpgrade) {
        this.dataUpgrade = dataUpgrade;
    }
}
