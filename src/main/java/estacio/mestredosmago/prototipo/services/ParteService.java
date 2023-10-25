package estacio.mestredosmago.prototipo.services;

import estacio.mestredosmago.prototipo.endereco.dtos.DadosCadastroEndereco;
import estacio.mestredosmago.prototipo.parte.dtos.DadosAtualizaParte;
import estacio.mestredosmago.prototipo.endereco.EnderecoParte;
import estacio.mestredosmago.prototipo.endereco.EnderecoParteRepository;
import estacio.mestredosmago.prototipo.parte.Parte;
import estacio.mestredosmago.prototipo.parte.ParteRepository;
import estacio.mestredosmago.prototipo.parte.dtos.DadosCadastroParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParte;
import estacio.mestredosmago.prototipo.parte.dtos.DadosListagemParteNoEndereco;
import estacio.mestredosmago.prototipo.processo.ProcessoRepository;
import estacio.mestredosmago.prototipo.services.viacep.ViaCepService;
import estacio.mestredosmago.prototipo.services.viacep.dto.ViaCepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ParteService {
    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    ProcessoRepository processoRepository;

    @Autowired
    EnderecoParteRepository enderecoRepository;

    @Autowired
    ParteRepository parteRepository;

    public ResponseEntity cadastraParte(DadosCadastroParte dados, String numProcesso, UriComponentsBuilder uriBuilder) {
        var processo = processoRepository.getReferenceById(numProcesso);
        EnderecoParte endereco = null;

        if(dados.endereco() != null){
            if(dados.endereco().logradouro() == null && dados.endereco().complemento() == null && dados.endereco().bairro() == null && dados.endereco().cidade() == null && dados.endereco().uf() == null) {
                endereco = new EnderecoParte(viaCepService.buscarDados(dados.endereco().cep()), dados.endereco());
                enderecoRepository.save(endereco);
            } else {
                endereco = new EnderecoParte(dados.endereco());
                enderecoRepository.save(endereco);
            }
        }

        
        var parte = new Parte(dados, processo, endereco);
        parteRepository.save(parte);

        var uri = uriBuilder.path("/partes/{id}").buildAndExpand(parte.getIdParte()).toUri();
        if (parte.getEndereco() != null){
            return ResponseEntity.created(uri).body(new DadosListagemParte(parte));
        }else {
            return ResponseEntity.created(uri).body(new DadosListagemParteNoEndereco(parte));
        }

    }

    public ResponseEntity<Page<DadosListagemParteNoEndereco>> listarPartes(Pageable paginacao) {

        List<DadosListagemParteNoEndereco> dados = parteRepository.findAll().stream().map(p -> new DadosListagemParteNoEndereco(p)).toList();

        return ResponseEntity.ok(new PageImpl<>(dados, paginacao, dados.size()));
    }


    public ResponseEntity atualizarParte(Long id, DadosAtualizaParte dados) {
        var parte = parteRepository.getReferenceById(id);
        EnderecoParte endereco = null;

        if(dados.endereco() != null && parte.getEndereco() != null) {
            if(dados.endereco().logradouro() == null && dados.endereco().complemento() == null && dados.endereco().bairro() == null && dados.endereco().cidade() == null && dados.endereco().uf() == null) {
                parte.getEndereco().atualizar(viaCepService.buscarDados(dados.endereco().cep()), dados.endereco());
            } else {
                parte.getEndereco().atualizar(dados.endereco());
            }

            parte.atualizar(dados);

        } else if (dados.endereco() != null) {
            if(dados.endereco().logradouro() == null && dados.endereco().complemento() == null && dados.endereco().bairro() == null && dados.endereco().cidade() == null && dados.endereco().uf() == null) {
                endereco = new EnderecoParte(viaCepService.buscarDados(dados.endereco().cep()), dados.endereco());
                enderecoRepository.save(endereco);
            } else {
                endereco = new EnderecoParte(dados.endereco());
                enderecoRepository.save(endereco);
            }

            parte.atualizar(dados, endereco);
        } else {
            parte.atualizar(dados);
        }



        if(parte.getEndereco() != null) {
            return ResponseEntity.ok(new DadosListagemParte(parte));
        } else {
            return ResponseEntity.ok(new DadosListagemParteNoEndereco(parte));
        }
    }

    public ResponseEntity excluir(Long id) {
        parteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity detalharParte(Long id) {
        var parte = parteRepository.getReferenceById(id);
        if(parte.getEndereco() != null) {
            return ResponseEntity.ok(new DadosListagemParte(parte));
        } else {
            return ResponseEntity.ok(new DadosListagemParteNoEndereco(parte));
        }

    }


}
