package estacio.mestredosmago.prototipo.processo.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProcesso(@NotBlank String numProcesso) {
}
