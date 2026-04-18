package br.com.senain116.autoescolan116.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        boolean ativo,
        Perfil perfil) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.isAtivo(),
                usuario.getPerfil()
        );
    }
}