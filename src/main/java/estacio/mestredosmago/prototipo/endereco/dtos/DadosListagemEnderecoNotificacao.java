package estacio.mestredosmago.prototipo.endereco.dtos;

import estacio.mestredosmago.prototipo.endereco.EnderecoNotificacao;
import estacio.mestredosmago.prototipo.endereco.EnderecoParte;

public record DadosListagemEnderecoNotificacao(
        Long idEndereco,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String numero
) {
    public DadosListagemEnderecoNotificacao(EnderecoNotificacao enderecoNotificacao) {

        this(enderecoNotificacao.getIdEndereco(),
                enderecoNotificacao.getCep(),
                enderecoNotificacao.getLogradouro(),
                enderecoNotificacao.getComplemento(),
                enderecoNotificacao.getBairro(),
                enderecoNotificacao.getCidade(),
                enderecoNotificacao.getUf(),
                enderecoNotificacao.getNumero()
        );
    }
}