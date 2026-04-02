package br.com.gustavo.espacoKids.domain.entity.usuario;

public enum Perfil {
    PROFESSORA("professora"),
    RESPONSAVEL("responsavel"),
    ADM("Administrador");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String toAuthority() {
        return "ROLE_" + this.name();
    }
}
