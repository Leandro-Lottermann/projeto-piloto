package estacio.mestredosmago.prototipo.parte.dtos;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosListagemEnderecoParte;
import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.processo.Processo;
import estacio.mestredosmago.prototipo.processo.dtos.DadosListagemProcesso;

public record DadosListagemParteNoEndereco(
        Long idParte,
        String nome,
        String documento,
        String email,
        DadosListagemProcesso processo

) {

    public DadosListagemParteNoEndereco(Parte parte) {

        this(parte.getIdParte(),
                parte.getNome(),
                parte.getDocumento(),
                parte.getEmail(),
                new DadosListagemProcesso(parte.getProcesso())
        );
    }
}