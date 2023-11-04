package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.parte.dtos.DadosAtualizaParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosCadastroParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParteNoEndereco;
import estacio.mestredosmago.prototipo.services.EmailSenderService;
import estacio.mestredosmago.prototipo.services.ParteService;
import estacio.mestredosmago.prototipo.services.viacep.ViaCepService;
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
@RequestMapping("partes")
public class ParteController {
    @Autowired
    private ParteService parteService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroParte dados, UriComponentsBuilder uriBuilder, @PathVariable String id) {
        return parteService.cadastraParte(dados, id, uriBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity emailCaique(@PathVariable Long id) {
       return parteService.detalharParte(id);
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemParteNoEndereco>> getPartes(@PageableDefault(size = 10, sort = {"statusProcesso"}) Pageable paginacao) {

        return parteService.listarPartes(paginacao);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaParte dados, @PathVariable Long id) {

       return parteService.atualizarParte(id, dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id) {

        return parteService.excluir(id);
    }






}
