package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.endereco.EnderecoNotificacao;
import estacio.mestredosmago.prototipo.endereco.EnderecoNotificacaoRepository;
import estacio.mestredosmago.prototipo.notificacao.Notificacao;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosCadastroNotificacao;
import estacio.mestredosmago.prototipo.notificacao.NotificacaoRepository;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhes;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhesNoEndereco;
import estacio.mestredosmago.prototipo.parte.ParteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository repository;

    @Autowired
    private ParteRepository parteRepository;

    @Autowired
    private EnderecoNotificacaoRepository enderecoRepository;

    @Autowired
    private EmailSenderService emailSenderService;



    public ResponseEntity cadastrarNotificacao(DadosCadastroNotificacao dados, UriComponentsBuilder uriBuilder) {
        var parte = parteRepository.getReferenceById(dados.idParte());

        var notificacao = new Notificacao(dados, parte);
        repository.save(notificacao);

        if(dados.concluida()) {
            this.enviarNotificacao(notificacao);
        }

        var uri = uriBuilder.path("/notificacao/{id}").buildAndExpand(notificacao.getIdNotificacao()).toUri();
        return ResponseEntity.created(uri).body(new DadosNotificacaoDetalhesNoEndereco(notificacao));
    }

    public ResponseEntity atualizaNotificacao(DadosCadastroNotificacao dados, Long id) {
        var notificacao = repository.getReferenceById(id);

        if(notificacao.getStatusNotificacao() == "incompleto"){
            notificacao.atualizar(dados);
            if(dados.concluida()) {
                this.enviarNotificacao(notificacao);
            }

            if(notificacao.getEndereco() != null) {
                return ResponseEntity.ok(new DadosNotificacaoDetalhes(notificacao));
            } else {
                return ResponseEntity.ok(new DadosNotificacaoDetalhesNoEndereco(notificacao));
            }
        } else {
            return ResponseEntity.badRequest().body("Impossível atualizar notificação: Já foi enviada e não pode ser alterada");
        }



    }

    public void enviarNotificacao(Notificacao notificacao) {
        if(notificacao.getParte().getEndereco() != null && notificacao.getTextoNotificacao() != null) {
            EnderecoNotificacao endereco = new EnderecoNotificacao(notificacao.getParte().getEndereco());
            enderecoRepository.save(endereco);
            notificacao.enviar("não notificado", "correios", endereco);
        } else if (notificacao.getParte().getEmail() != null && notificacao.getTextoNotificacao() != null) {
            notificacao.enviar("notificado", "email");
            this.emailSenderService.enviar(notificacao.getParte().getEmail(), "Notificação Judicial", notificacao.getTextoNotificacao());
        } else if (notificacao.getTextoNotificacao() != null) {
            notificacao.enviar("não notificado", "DJe");
        }
    }

    public ResponseEntity deletar(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<DadosNotificacaoDetalhesNoEndereco>> listar(Pageable paginacao) {
        List<DadosNotificacaoDetalhesNoEndereco> lista = repository.findAll().stream().map(DadosNotificacaoDetalhesNoEndereco::new).toList();
        return ResponseEntity.ok(new PageImpl<>(lista, paginacao, lista.size()));
    }
}
