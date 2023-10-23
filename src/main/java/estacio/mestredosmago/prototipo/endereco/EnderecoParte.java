package estacio.mestredosmago.prototipo.endereco;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosCadastroEndereco;
import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.services.viacep.dto.ViaCepDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Table(name = "Enderecos_Partes")
@Entity(name = "EnderecoParte")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_endereco")
public class EnderecoParte {

    @OneToOne(mappedBy = "endereco")
    private Parte parte;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;

    public  EnderecoParte(DadosCadastroEndereco dados) {
            this.cep = dados.cep();
            this.logradouro = dados.logradouro();
            this.complemento = dados.complemento();
            this.bairro = dados.bairro();
            this.cidade = dados.cidade();
            this.uf = dados.uf();
            this.numero = dados.numero();
    }

    public  EnderecoParte(ViaCepDto dadosViaCep, DadosCadastroEndereco dados) {
        this.cep = dadosViaCep.cep();
        this.logradouro = dadosViaCep.logradouro();
        this.complemento = dadosViaCep.complemento();
        this.bairro = dadosViaCep.bairro();
        this.cidade = dadosViaCep.localidade();
        this.uf = dadosViaCep.uf();
        this.numero = dados.numero();
    }


    public void atualizar(DadosCadastroEndereco dados) {
            this.cep = dados.cep();
            this.logradouro = dados.logradouro();
            this.complemento = dados.complemento();
            this.bairro = dados.bairro();
            this.cidade = dados.cidade();
            this.uf = dados.uf();
            this.numero = dados.numero();
    }

    public void atualizar(ViaCepDto dadosViaCep, DadosCadastroEndereco dados) {
        this.cep = dadosViaCep.cep();
        this.logradouro = dadosViaCep.logradouro();
        this.complemento = dadosViaCep.complemento();
        this.bairro = dadosViaCep.bairro();
        this.cidade = dadosViaCep.localidade();
        this.uf = dadosViaCep.uf();
        this.numero = dados.numero();
    }
}
