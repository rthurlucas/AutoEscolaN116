package br.com.senain116.autoescolan116.domain.usuario;

public record DadosListagemUsuario(Long id, String login, Perfil perfil) {
    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getPerfil());
    }
}