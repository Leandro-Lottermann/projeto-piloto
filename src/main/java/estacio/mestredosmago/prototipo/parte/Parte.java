package estacio.mestredosmago.prototipo.parte;

import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.parte.dtos.DadosAtualizaParte;
import estacio.mestredosmago.prototipo.endereco.EnderecoParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosCadastroParte;
import estacio.mestredosmago.prototipo.processo.Processo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity(name = "Partes")
@Table(name = "Partes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idParte")
public class Parte {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParte;

    @ManyToOne
    @JoinColumn(name = "num_processo", nullable = false)
    private Processo processo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private EnderecoParte endereco;

    private String nome;
    private String documento;
    private String email;

    @OneToOne(mappedBy = "parte")
    private Notificacao notificacao;



    public Parte(DadosCadastroParte dados, Processo processo, EnderecoParte endereco) {
        this.processo = processo;
        this.nome = dados.nome();
        this.documento = dados.documento();
        this.email = dados.email();
        if(endereco != null) {
            this.endereco = endereco;
        }

    }

    public void atualizar(DadosAtualizaParte dados) {
        if(dados.email() != null) {
            this.email = dados.email();
        }
        if(this.documento == null && dados.documento() != null) {
            this.documento = dados.documento();
        }


    }



}
