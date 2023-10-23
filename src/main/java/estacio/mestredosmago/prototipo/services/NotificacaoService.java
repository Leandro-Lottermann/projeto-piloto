package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosCadastroNotificacao;
import estacio.mestredosmago.prototipo.notificacao.NotificacaoRepository;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhes;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhesNoEndereco;
import estacio.mestredosmago.prototipo.parte.ParteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NotificacaoService {
    @Autowired
    NotificacaoRepository repository;

    @Autowired
    ParteRepository parteRepository;



    public ResponseEntity cadastrarNotificacao(DadosCadastroNotificacao dados, UriComponentsBuilder uriBuilder) {
        var parte = parteRepository.getReferenceById(dados.idParte());

        var notificacao = new Notificacao(dados, parte);
        System.out.println(notificacao.getEndereco());

        repository.save(notificacao);

        var uri = uriBuilder.path("/partes/{id}").buildAndExpand(notificacao.getIdNotificacao()).toUri();
        return ResponseEntity.created(uri).body(new DadosNotificacaoDetalhesNoEndereco(notificacao));
    }
}
