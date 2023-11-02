package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.notificacao.NotificacaoRepository;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhesNoEndereco;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DJEService {
    @Autowired
    NotificacaoRepository repository;
    public ResponseEntity<Page<DadosNotificacaoDetalhesNoEndereco>> getNotificaceos(Pageable paginacao) {
        var notificacoes = repository.findByStatusNotificacaoAndFormaEnvio("não notificado", "DJE")
                .stream().map(DadosNotificacaoDetalhesNoEndereco::new)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(notificacoes, paginacao, notificacoes.size()));
    }

    @Transactional
    public ResponseEntity publicaNotificacao(Long id) {
        var notificacao = repository.getReferenceById(id);
        notificacao.atualizarStatus("notificado");
        return ResponseEntity.ok().build();
    }
}
