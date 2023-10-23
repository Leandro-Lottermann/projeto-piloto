package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.parte.dtos.DadosAtualizaParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosCadastroParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.services.ParteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("partes")
public class ParteController {
    @Autowired
    private ParteService parteService;


    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroParte dados, UriComponentsBuilder uriBuilder, @PathVariable String id) {
        return parteService.cadastraParte(dados, id, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemParte>> getPartes(@PageableDefault(size = 10, sort = {"statusProcesso"}) Pageable paginacao) {

        return parteService.listarPartes(paginacao);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizaParte dados, @PathVariable Long id) {

        return parteService.atualizarParte(id, dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id) {

        return parteService.excluir(id);
    }






}
