package estacio.mestredosmago.prototipo.parte;

import estacio.mestredosmago.prototipo.parte.dtos.DadosCadastroParte;
import estacio.mestredosmago.prototipo.processo.Processo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String nome;
    private String documento;
    private String email;
//    private String cep;
//    private String logradouro;
//    private String complemento;
//    private String bairro;
//    private String cidade;
//    private String uf;
//    private String numero;


    public Parte(DadosCadastroParte dados, Processo processo) {
        this.processo = processo;
        this.nome = dados.nome();
        this.documento = dados.documento();
        this.email = dados.email();
//        this.cep = dados.cep();
//        this.logradouro = dados.logradouro();
//        this.complemento = dados.complemento();
//        this.bairro = dados.bairro();
//        this.cidade = dados.cidade();
//        this.uf = dados.uf();
//        this.numero = dados.numero();

    }



}
