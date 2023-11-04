package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.notificacao.dtos.DadosAtualizaNotificacao;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosCadastroNotificacao;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhes;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhesNoEndereco;
import estacio.mestredosmago.prototipo.services.NotificacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("notificacoes")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody  @Valid DadosCadastroNotificacao dados, UriComponentsBuilder uriBuilder) {

         return notificacaoService.cadastrarNotificacao(dados, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaNotificacao dados, @PathVariable Long id) {
        return notificacaoService.atualizaNotificacao(dados, id);
    }

    @GetMapping
    public ResponseEntity<Page<DadosNotificacaoDetalhesNoEndereco>> listar(@PageableDefault(size = 10, sort = {"statusProcesso"}) Pageable paginacao) {
        return notificacaoService.listar(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharNotificacao(@PathVariable Long id) {
        return notificacaoService.detalhar(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        return notificacaoService.deletar(id);
    }


}
