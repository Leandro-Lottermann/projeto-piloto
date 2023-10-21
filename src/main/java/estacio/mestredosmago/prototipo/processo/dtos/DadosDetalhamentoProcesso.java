package estacio.mestredosmago.prototipo.processo.dtos;

import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.processo.Processo;

import java.util.List;

public record DadosDetalhamentoProcesso(String numProcesso, String statusProcesso, List<DadosListagemParte> partes) {

    public DadosDetalhamentoProcesso(Processo processo, List<DadosListagemParte> partes) {
        this(processo.getNumProcesso(), processo.getStatusProcesso(), partes);


    }

}