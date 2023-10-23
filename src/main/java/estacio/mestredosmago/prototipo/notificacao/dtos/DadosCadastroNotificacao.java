package estacio.mestredosmago.prototipo.notificacao.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroNotificacao(

        Long idParte,
        String textoNotificacao,
        String statusNotificacao

) {
}
