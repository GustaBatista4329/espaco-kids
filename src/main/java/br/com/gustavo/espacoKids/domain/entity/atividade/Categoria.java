package br.com.gustavo.espacoKids.domain.entity.atividade;

public enum Categoria {
    PORTUGUES("Português"),
    MATEMATICA("Matemática"),
    CIENCIAS("Ciências"),
    HISTORIA("História"),
    GEOGRAFIA("Geografia"),
    INGLES("Inglês"),
    ARTES("Artes"),
    GERAL("Geral");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
