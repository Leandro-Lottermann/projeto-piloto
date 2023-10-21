package estacio.mestredosmago.prototipo.parte.dtos;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosListagemEnderecoParte;
import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.processo.Processo;
import estacio.mestredosmago.prototipo.processo.dtos.DadosListagemProcesso;

public record DadosListagemParte(
        Long idParte,
        String nome,
        String documento,
        String email,
        DadosListagemProcesso processo,
        DadosListagemEnderecoParte endereco
) {

    public DadosListagemParte(Parte parte) {

        this(parte.getIdParte(),
                parte.getNome(),
                parte.getDocumento(),
                parte.getEmail(),
                new DadosListagemProcesso(parte.getProcesso()),
                new DadosListagemEnderecoParte(parte.getEndereco())
        );
    }
}
