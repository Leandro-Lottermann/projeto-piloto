package estacio.mestredosmago.prototipo.parte.dtos;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroParte(
        @NotBlank
        String nome,
        String documento,
        String email,
        @Valid
        DadosCadastroEndereco endereco
) {
}
