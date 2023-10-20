package estacio.mestredosmago.prototipo.parte.dtos;

import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.processo.Processo;
import estacio.mestredosmago.prototipo.processo.dtos.DadosListagemProcesso;

public record DadosListagemParte(
        DadosListagemProcesso processo,
        Long idParte,
        String nome,
        String documento,
        String email
) {

    public DadosListagemParte(Parte parte) {
        this(new DadosListagemProcesso(parte.getProcesso()), parte.getIdParte(), parte.getNome(), parte.getDocumento(), parte.getEmail());
    }
}
