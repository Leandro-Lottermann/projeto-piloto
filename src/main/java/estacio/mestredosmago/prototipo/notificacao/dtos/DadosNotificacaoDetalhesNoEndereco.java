package estacio.mestredosmago.prototipo.notificacao.dtos;

import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;

import java.sql.Timestamp;

public record DadosNotificacaoDetalhesNoEndereco(Long idNotificacao, DadosListagemParte parte, String textoNotificacao, Timestamp dataEnvio, String statusNotificacao, String formaEnvio, String statusEnvio) {
    public DadosNotificacaoDetalhesNoEndereco(Notificacao notificacao) {
        this(notificacao.getIdNotificacao(), new DadosListagemParte(notificacao.getParte()), notificacao.getTextoNotificacao(), notificacao.getDataEnvio(), notificacao.getStatusNotificacao(), notificacao.getFormaEnvio(), notificacao.getStatusEnvio());
    }
}
