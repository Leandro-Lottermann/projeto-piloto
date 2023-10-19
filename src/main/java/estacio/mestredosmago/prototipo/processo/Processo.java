package estacio.mestredosmago.prototipo.processo;

import estacio.mestredosmago.prototipo.processo.dtos.DadosCadastroProcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Processos")
@Entity(name = "Processo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "numProcesso")
public class Processo {
    @Id
    private String numProcesso;
    private String statusProcesso;

    public Processo(DadosCadastroProcesso dados) {
        this.numProcesso = dados.numProcesso();
        this.statusProcesso = "SEM NOTIFICAÇÃO";
    }
}
