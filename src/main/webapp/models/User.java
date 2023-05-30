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


}
