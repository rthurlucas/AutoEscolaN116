package br.com.senain116.autoescolan116.domain.instrucao;

import br.com.senain116.autoescolan116.domain.aluno.Aluno;
import br.com.senain116.autoescolan116.domain.instrutor.Especialidade;
import br.com.senain116.autoescolan116.domain.instrutor.Instrutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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
    @JoinColumn(name = "alunos_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutores_id")
    private Instrutor instrutor;

    @Column(name = "data_hora")
    private LocalDateTime datahora;
    
    private boolean ativo = true;

    @Enumerated
    private MotivoCancelamento motivoCancelamento;

    public Instrucao(Long id, Aluno aluno, Instrutor instrutor, LocalDateTime datahora) {
        this.id = id;
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.datahora = datahora;
        this.ativo = true;
    }

    public Instrucao(Long aLong, MotivoCancelamento motivoCancelamento) {
    }

    public void excluir(){
        this.ativo = false;
   }
}
