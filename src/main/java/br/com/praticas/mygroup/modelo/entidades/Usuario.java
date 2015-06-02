package br.com.praticas.mygroup.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String login;
    private String senha;
    @Column(unique = true, nullable = false)
    private String email;
    @Lob
    private byte[] fotoPerfil;
    @ManyToMany(mappedBy = "usuarios")
    private List<Sala> salasParticipando;
    @OneToMany(mappedBy = "usuarioPost")
    private List<Postagem> postagens;
    @OneToMany(mappedBy = "usuarioUpgrade")
    private List<Documento> documentoUpgrades;
    @OneToMany(mappedBy = "remetente")
    private List<Mensagem> mensagensEnviadas;
    @OneToMany(mappedBy = "destinatario")
    private List<Mensagem> mensagensRecebidas;
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public void setMensagensEnviadas(List<Mensagem> mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

    public List<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    public void setMensagensRecebidas(List<Mensagem> mensagensRecebidas) {
        this.mensagensRecebidas = mensagensRecebidas;
    }

    public List<Documento> getDocumentoUpgrades() {
        return documentoUpgrades;
    }

    public void setDocumentoUpgrades(List<Documento> documentoUpgrades) {
        this.documentoUpgrades = documentoUpgrades;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Sala> getSalasParticipando() {
        return salasParticipando;
    }

    public void setSalasParticipando(List<Sala> salasParticipando) {
        this.salasParticipando = salasParticipando;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    @Override
    public String toString() {
        return "" + "Nome: " + this.nome + " - E-mail: " + this.email + " - Postagens: " + getPostagens() + "\n";
    }

}
