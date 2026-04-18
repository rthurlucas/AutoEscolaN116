package br.com.senain116.autoescolan116.domain.aluno;

public record DadosListagemAluno(
        Long id,
        String nome,
        String email) {
    public DadosListagemAluno(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail()
        );
    }
}