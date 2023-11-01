package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.notificacao.NotificacaoRepository;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosAtualizacaoECarta;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorreiosService {
    @Autowired
    NotificacaoRepository repository;

    public ResponseEntity listarLote() {
        var notificacoes = repository.findByStatusNotificacaoAndFormaEnvio("não notificado", "correios").stream().map(n -> {
            return new DadosNotificacaoDetalhes(n);
        }).toList();
        if (notificacoes.size() > 9) {
            return ResponseEntity.ok().body(notificacoes);

        } else {
            return ResponseEntity.ok("sem dados");

        }

    }

    @Transactional
    public ResponseEntity atualizaLote(List<DadosNotificacaoDetalhes> listaDados) {
        List<Notificacao> listaEntidades = listaDados.stream().map(d -> {
            return repository.getReferenceById(d.idNotificacao());
        }).toList();

        listaEntidades.forEach(i -> i.atualizarStatus("transito"));


        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity atualizaStatusEnvio(Long id, DadosAtualizacaoECarta dados) {
        var notificacao = repository.getReferenceById(id);
        notificacao.atualizarStatus(dados.statusNotificacao());
        return ResponseEntity.ok(new DadosNotificacaoDetalhes(notificacao));
    }
}
