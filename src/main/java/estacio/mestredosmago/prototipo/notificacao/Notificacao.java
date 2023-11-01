package estacio.mestredosmago.prototipo.notificacao;

import estacio.mestredosmago.prototipo.endereco.EnderecoNotificacao;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosCadastroNotificacao;
import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.processo.Processo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "Notificacoes")
@Entity(name = "Notificacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idNotificacao")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacao;

    @ManyToOne
    @JoinColumn(name = "id_parte")
    private Parte parte;


    @OneToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoNotificacao endereco;

    private String textoNotificacao;
    private Timestamp dataEnvio;
    private String statusNotificacao; //incompleto, nao notificado, notificado
    private String formaEnvio;


    public Notificacao(DadosCadastroNotificacao dados, Parte parte) {
        this.parte = parte;

        if (dados.textoNotificacao() != null){
            this.textoNotificacao = dados.textoNotificacao();
        }

        this.statusNotificacao = "incompleto";

    }

    public void atualizar(DadosCadastroNotificacao dados) {
        this.textoNotificacao = dados.textoNotificacao();
    }

    public void enviar(String statusNotificacao, String formaEnvio, EnderecoNotificacao endereco) {
        this.statusNotificacao = statusNotificacao;
        this.formaEnvio = formaEnvio;
        this.endereco = endereco;
    }

    public void enviar(String statusNotificacao, String formaEnvio) {
        this.statusNotificacao = statusNotificacao;
        this.formaEnvio = formaEnvio;

    }

    public void atualizarStatus(String status) {
        this.statusNotificacao = status;
    }
}
