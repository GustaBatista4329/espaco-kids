package br.com.gustavo.espacoKids.entity.usuario;

public enum Perfil {
    PROFESSORA("professora"),
    RESPONSAVEL("responsavel"),
    ADM("Administrador");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public static Perfil fromString(String text) {
        for (Perfil perfil : Perfil.values()) {
            if (perfil.descricao.equalsIgnoreCase(text)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
