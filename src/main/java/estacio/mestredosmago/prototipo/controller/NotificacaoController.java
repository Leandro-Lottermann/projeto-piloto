package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.notificacao.dtos.DadosCadastroNotificacao;
import estacio.mestredosmago.prototipo.services.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("notificacoes")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody  DadosCadastroNotificacao dados, UriComponentsBuilder uriBuilder) {

         return notificacaoService.cadastrarNotificacao(dados, uriBuilder);
    }
}
