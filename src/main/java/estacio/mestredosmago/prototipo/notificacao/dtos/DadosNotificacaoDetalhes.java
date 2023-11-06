package estacio.mestredosmago.prototipo.notificacao.dtos;

import estacio.mestredosmago.prototipo.endereco.EnderecoNotificacao;
import estacio.mestredosmago.prototipo.endereco.dtos.DadosListagemEnderecoNotificacao;
import estacio.mestredosmago.prototipo.endereco.dtos.DadosListagemEnderecoParte;
import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParteNoEndereco;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record DadosNotificacaoDetalhes(Long idNotificacao, DadosListagemParteNoEndereco parte, DadosListagemEnderecoNotificacao endereco, String textoNotificacao, LocalDateTime dataEnvio, String statusNotificacao, String formaEnvio) {
    public DadosNotificacaoDetalhes(Notificacao notificacao) {
        this(notificacao.getIdNotificacao(), new DadosListagemParteNoEndereco(notificacao.getParte()), new DadosListagemEnderecoNotificacao(notificacao.getEndereco()), notificacao.getTextoNotificacao(), notificacao.getDataEnvio(), notificacao.getStatusNotificacao(), notificacao.getFormaEnvio());
    }
}
