package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//aqui todos os servicos relacionados aos controllers
@Service
public class ProcessoService {
    public List<DadosListagemParte> criaLista(List<Parte> lista) {
        List<DadosListagemParte> listaPartes = new ArrayList<>();
        lista.forEach(p -> {
            listaPartes.add(new DadosListagemParte(p));
        });
        return  listaPartes;
    }
}

