package br.com.gustavo.espacoKids.infra.config.scheduler;

import br.com.gustavo.espacoKids.service.atividadeService.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AtividadeScheduler {

    private final AtividadeService atividadeService;

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional
    public void limparAtividadesExpiradas() {
        atividadeService.removerAtividadesExpiradas();
    }
}
