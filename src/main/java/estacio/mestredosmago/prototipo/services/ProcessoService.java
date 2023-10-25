package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.processo.Processo;
import estacio.mestredosmago.prototipo.processo.ProcessoRepository;
import estacio.mestredosmago.prototipo.processo.dtos.DadosCadastroProcesso;
import estacio.mestredosmago.prototipo.processo.dtos.DadosDetalhamentoProcesso;
import estacio.mestredosmago.prototipo.processo.dtos.DadosListagemProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


//aqui todos os servicos relacionados aos controllers
@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository repository;
    public List<DadosListagemParte> criaLista(List<Parte> lista) {
        List<DadosListagemParte> listaPartes = new ArrayList<>();
        lista.forEach(p -> {
            listaPartes.add(new DadosListagemParte(p));
        });
        return  listaPartes;
    }

    public ResponseEntity cadastraProcesso(DadosCadastroProcesso dados, UriComponentsBuilder uriBuilder) {
        var processo = new Processo(dados);
        repository.save(processo);

        var uri = uriBuilder.path("/processos/{id}").buildAndExpand(processo.getNumProcesso()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemProcesso(processo));
    }

    public ResponseEntity<DadosDetalhamentoProcesso> detalharProcesso(String id) {
        var processo = repository.getReferenceById(id);
        var partes = this.criaLista(processo.getPartes());
        return ResponseEntity.ok(new DadosDetalhamentoProcesso(processo, partes));
    }

    public ResponseEntity<Page<DadosListagemProcesso>> listarProcesos(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemProcesso::new);
    //ajustar para retornar uma lista de partes e notificacoes

        return ResponseEntity.ok(page);
    }


    public ResponseEntity excluirProcesso(String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

