package estacio.mestredosmago.prototipo.controller;

import estacio.mestredosmago.prototipo.services.DJEService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dje")
public class DJEController {
    @Autowired
    private DJEService djeService;

    @GetMapping
    public ResponseEntity getNotificacoes(@PageableDefault(size = 10, sort = {"statusProcesso"}) Pageable paginacao) {
        return djeService.getNotificaceos(paginacao);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity publicaNotificacao(@PathVariable Long id) {
        return djeService.publicaNotificacao(id);
    }
}
