package br.com.gustavo.espacoKids.service.atividadeService;

import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.AtividadeAlunoDTO;
import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.AtribuirAtividadeDTO;
import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.BancoAtividadeDTO;
import br.com.gustavo.espacoKids.domain.entity.atividade.AtividadeAluno;
import br.com.gustavo.espacoKids.domain.entity.atividade.BancoAtividade;
import br.com.gustavo.espacoKids.repository.AlunoRepository;
import br.com.gustavo.espacoKids.repository.AtividadeAlunoRepository;
import br.com.gustavo.espacoKids.repository.BancoAtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final BancoAtividadeRepository bancoAtividadeRepository;
    private final AtividadeAlunoRepository atividadeAlunoRepository;
    private final AlunoRepository alunoRepository;
    private final Path atividadesUploadPath;

    public BancoAtividadeDTO uploadAtividade(String titulo, String descricao, MultipartFile arquivo) {
        if (arquivo.isEmpty() || !"application/pdf".equals(arquivo.getContentType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O arquivo deve ser um PDF válido");
        }

        String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        Path destino = atividadesUploadPath.resolve(nomeArquivo);

        try {
            Files.copy(arquivo.getInputStream(), destino);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar o arquivo");
        }

        var bancoAtividade = new BancoAtividade();
        bancoAtividade.setTitulo(titulo);
        bancoAtividade.setDescricao(descricao);
        bancoAtividade.setNomeArquivo(nomeArquivo);

        return new BancoAtividadeDTO(bancoAtividadeRepository.save(bancoAtividade));
    }

    public List<BancoAtividadeDTO> listarBanco() {
        return bancoAtividadeRepository.findAllByOrderByCriadoEmDesc().stream()
                .map(BancoAtividadeDTO::new)
                .toList();
    }

    @Transactional
    public void excluirDoBanco(Long id) {
        var atividade = bancoAtividadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada"));

        atividadeAlunoRepository.deleteByBancoAtividadeId(id);

        Path arquivo = atividadesUploadPath.resolve(atividade.getNomeArquivo());
        try {
            Files.deleteIfExists(arquivo);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar o arquivo");
        }

        bancoAtividadeRepository.delete(atividade);
    }

    public AtividadeAlunoDTO atribuirParaAluno(AtribuirAtividadeDTO dto) {
        var bancoAtividade = bancoAtividadeRepository.findById(dto.bancoAtividadeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada no banco"));

        var aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        if (atividadeAlunoRepository.existsByBancoAtividadeIdAndAlunoId(dto.bancoAtividadeId(), dto.alunoId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Atividade já atribuída a este aluno");
        }

        var atividadeAluno = new AtividadeAluno();
        atividadeAluno.setBancoAtividade(bancoAtividade);
        atividadeAluno.setAluno(aluno);

        return new AtividadeAlunoDTO(atividadeAlunoRepository.save(atividadeAluno));
    }

    public List<AtividadeAlunoDTO> listarAtividadesDoAluno(Long alunoId) {
        LocalDateTime agora = LocalDateTime.now();
        return atividadeAlunoRepository.findByAlunoId(alunoId).stream()
                .filter(a -> a.getDataAtribuicao().plusDays(7).isAfter(agora))
                .map(AtividadeAlunoDTO::new)
                .toList();
    }

    public void removerAtribuicao(Long id) {
        if (!atividadeAlunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atribuição não encontrada");
        }
        atividadeAlunoRepository.deleteById(id);
    }

    @Transactional
    public void removerAtividadesExpiradas() {
        atividadeAlunoRepository.deleteByDataAtribuicaoBefore(LocalDateTime.now().minusDays(7));
    }
}
