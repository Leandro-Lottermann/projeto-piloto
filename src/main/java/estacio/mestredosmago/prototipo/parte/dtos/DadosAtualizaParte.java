package estacio.mestredosmago.prototipo.parte.dtos;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosCadastroEndereco;
import estacio.mestredosmago.prototipo.endereco.dtos.DadosListagemEnderecoParte;
import jakarta.validation.Valid;

public record DadosAtualizaParte(
        String nome,
        String documento,
        String email,
        @Valid
        DadosCadastroEndereco endereco
) {
}
