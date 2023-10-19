package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.processo.Processo;
import estacio.mestredosmago.prototipo.processo.ProcessoRepository;
import estacio.mestredosmago.prototipo.processo.dtos.DadosCadastroProcesso;
import estacio.mestredosmago.prototipo.processo.dtos.DadosListagemProcesso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("processos")
public class ProcessoController {
    @Autowired
    private ProcessoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProcesso>> getProcessos(@PageableDefault(size = 10, sort = {"statusProcesso"})Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemProcesso::new);


        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProcesso dados, UriComponentsBuilder uriBuilder) {
        var processo = new Processo(dados);
        repository.save(processo);

        var uri = uriBuilder.path("/processos/{id}").buildAndExpand(processo.getNumProcesso()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemProcesso(processo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemProcesso> detalhar(@PathVariable String id) {

        var processo = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosListagemProcesso(processo));
    }


}
