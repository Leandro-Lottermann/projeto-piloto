package estacio.mestredosmago.prototipo.parte.dtos;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroParte(
        @NotBlank
        String nome,
        @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
        String documento,
        @Email
        String email,
        @Valid
        DadosCadastroEndereco endereco
) {
}
