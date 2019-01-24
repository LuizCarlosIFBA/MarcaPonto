package br.com.rlimanogueira.marcaponto.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Long id_user;
    private Long idEmpresa;
    private String user_nome;
    private String user_setor;

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getUser_nome() {
        return user_nome;
    }

    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }

    public String getUser_setor() {
        return user_setor;
    }

    public void setUser_setor(String user_setor) {
        this.user_setor = user_setor;
    }
}
