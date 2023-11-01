package estacio.mestredosmago.prototipo.notificacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByStatusNotificacaoAndFormaEnvio(String statusNotificacao, String formaEnvio);
}
