package estacio.mestredosmago.prototipo.endereco.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEndereco(
        @NotBlank
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        @NotBlank
        String numero
) {
}
