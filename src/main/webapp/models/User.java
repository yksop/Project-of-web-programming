package models;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private enum TipoIscrizione { SIMPATIZZANTE, ADERENTE };

    private String nome;
    private String cognome;
    private Date dataNascita;
    private String email;
    private String telefono;
    private TipoIscrizione tipoIscrizione;
    private String username;
    private String password;

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public TipoIscrizione getTipoIscrizione() {
        return tipoIscrizione;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipoIscrizione(TipoIscrizione tipoIscrizione) {
        this.tipoIscrizione = tipoIscrizione;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
