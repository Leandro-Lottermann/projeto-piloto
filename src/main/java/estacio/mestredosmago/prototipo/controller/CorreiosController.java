package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.notificacao.dtos.DadosAtualizacaoECarta;
import estacio.mestredosmago.prototipo.notificacao.dtos.DadosNotificacaoDetalhes;
import estacio.mestredosmago.prototipo.services.CorreiosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ecarta")
public class CorreiosController {
    @Autowired
    CorreiosService correiosService;

    @GetMapping
    public ResponseEntity getLote() {
        return correiosService.listarLote();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaStatusLote(@RequestBody List<DadosNotificacaoDetalhes> dados) {

        return this.correiosService.atualizaLote(dados);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizaStatusNotificacao(@PathVariable Long id, @RequestBody DadosAtualizacaoECarta dados) {
        return this.correiosService.atualizaStatusEnvio(id, dados);
    }
}
