package estacio.mestredosmago.prototipo.endereco;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosCadastroEndereco;
import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Enderecos_Notificacoes")
@Entity(name = "EnderecoNotificacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_endereco")
public class EnderecoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;

    @OneToOne(mappedBy = "endereco")
    Notificacao notificacao;


}
