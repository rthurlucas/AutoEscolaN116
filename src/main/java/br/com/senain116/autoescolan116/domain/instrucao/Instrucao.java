package br.com.senain116.autoescolan116.domain.instrucao;

import br.com.senain116.autoescolan116.domain.aluno.Aluno;
import br.com.senain116.autoescolan116.domain.instrutor.Especialidade;
import br.com.senain116.autoescolan116.domain.instrutor.Instrutor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Instrucao")
@Table(name = "Instrucoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Instrucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    @JoinColumn(name = "data_hora")
    private LocalDateTime datahora;

    public void excluir(Instrucao instrucao){
        this.excluir(instrucao);
    }
}
