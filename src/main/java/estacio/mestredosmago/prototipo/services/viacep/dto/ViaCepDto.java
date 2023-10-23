package estacio.mestredosmago.prototipo.services.viacep.dto;

public record ViaCepDto(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
