package br.com.senain116.autoescolan116.domain.aluno;

import br.com.senain116.autoescolan116.domain.endereco.DadosEndereco;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco,
        boolean ativo) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf(),
                new DadosEndereco(
                        aluno.getEndereco().getLogradouro(),
                        aluno.getEndereco().getNumero(),
                        aluno.getEndereco().getComplemento(),
                        aluno.getEndereco().getBairro(),
                        aluno.getEndereco().getCidade(),
                        aluno.getEndereco().getUf(),
                        aluno.getEndereco().getCep()
                ),
                aluno.isAtivo()
        );
    }
}