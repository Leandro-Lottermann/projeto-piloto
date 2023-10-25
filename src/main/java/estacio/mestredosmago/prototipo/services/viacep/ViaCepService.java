package estacio.mestredosmago.prototipo.services.viacep;

import estacio.mestredosmago.prototipo.services.viacep.dto.ViaCepDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ViaCepService {
    public ViaCepDto buscarDados(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", cep),  ViaCepDto.class).getBody();

    }
}
