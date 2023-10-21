package estacio.mestredosmago.prototipo.endereco.dtos;

import estacio.mestredosmago.prototipo.endereco.EnderecoParte;

public record DadosListagemEnderecoParte(
        Long idEndereco,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String numero
) {
    public DadosListagemEnderecoParte(EnderecoParte enderecoParte) {

        this(enderecoParte.getIdEndereco(),
                enderecoParte.getCep(),
                enderecoParte.getLogradouro(),
                enderecoParte.getComplemento(),
                enderecoParte.getBairro(),
                enderecoParte.getCidade(),
                enderecoParte.getUf(),
                enderecoParte.getNumero()
        );
    }
}
