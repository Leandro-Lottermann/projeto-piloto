package estacio.mestredosmago.prototipo.endereco.dtos;

public record DadosCadastroEndereco(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String numero
) {
}
