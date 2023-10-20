package estacio.mestredosmago.prototipo.parte.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroParte(
        @NotBlank
        String numProcesso,

        String nome,
        String documento,
        String email
) {
}
