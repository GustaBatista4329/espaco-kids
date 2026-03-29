package br.com.gustavo.espacoKids.entity.horarioAula;

public enum DiaSemana {
    SEG("Segunda"),
    TER("Terça"),
    QUA("Quarta"),
    QUI("Quinta"),
    SEX("Sexta"),
    SAB("Sabádo");

    private String descricao;

    DiaSemana(String descricao) {
        this.descricao = descricao;
    }

    public static DiaSemana fromString(String text) {
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diaSemana.descricao.equalsIgnoreCase(text)) {
                return diaSemana;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
