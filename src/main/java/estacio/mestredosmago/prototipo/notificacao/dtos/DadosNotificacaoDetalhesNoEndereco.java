package estacio.mestredosmago.prototipo.notificacao.dtos;

import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParteNoEndereco;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record DadosNotificacaoDetalhesNoEndereco(Long idNotificacao, DadosListagemParteNoEndereco parte, String textoNotificacao, LocalDateTime dataEnvio, String statusNotificacao, String formaEnvio) {
    public DadosNotificacaoDetalhesNoEndereco(Notificacao notificacao) {
        this(notificacao.getIdNotificacao(), new DadosListagemParteNoEndereco(notificacao.getParte()), notificacao.getTextoNotificacao(), notificacao.getDataEnvio(), notificacao.getStatusNotificacao(), notificacao.getFormaEnvio());
    }
}
