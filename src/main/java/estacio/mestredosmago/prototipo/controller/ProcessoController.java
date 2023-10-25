package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.processo.Processo;
import estacio.mestredosmago.prototipo.processo.ProcessoRepository;
import estacio.mestredosmago.prototipo.processo.dtos.DadosCadastroProcesso;
import estacio.mestredosmago.prototipo.processo.dtos.DadosDetalhamentoProcesso;
import estacio.mestredosmago.prototipo.processo.dtos.DadosListagemProcesso;
import estacio.mestredosmago.prototipo.services.ProcessoService;
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
@RequestMapping("processos")
public class ProcessoController {
    @Autowired
    private ProcessoRepository repository;
    @Autowired
    private ProcessoService processoService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProcesso>> getProcessos(@PageableDefault(size = 10, sort = {"statusProcesso"})Pageable paginacao) {
        return processoService.listarProcesos(paginacao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProcesso dados, UriComponentsBuilder uriBuilder) {
        return processoService.cadastraProcesso(dados, uriBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProcesso> detalhar(@PathVariable String id) {
    //adicionar lista de partes e notificacoes
       return processoService.detalharProcesso(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String id) {

        return processoService.excluirProcesso(id);
    }


}
