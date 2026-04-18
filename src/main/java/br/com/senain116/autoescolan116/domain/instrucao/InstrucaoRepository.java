package br.com.senain116.autoescolan116.domain.instrucao;

import br.com.senain116.autoescolan116.domain.instrutor.Especialidade;
import br.com.senain116.autoescolan116.domain.instrutor.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
    @Query("""
        SELECT i FROM Instrutor i 
        WHERE 
        i.ativo = TRUE
        AND  
        i.especialidade = :especialidade
        AND
        i.id NOT IN (
            SELECT a.instrutor.id FROM Instrucao a 
            WHERE 
            a.datahora = :dataHora
            )    
        order by rand()
        limit 1
    """)
    Instrutor escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime data11);
}
