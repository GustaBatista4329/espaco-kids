package br.com.gustavo.espacoKids.service.alunoService;

import br.com.gustavo.espacoKids.domain.dto.alunoDTO.AlunoDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.domain.entity.aluno.Aluno;
import br.com.gustavo.espacoKids.repository.AlunoRepository;
import br.com.gustavo.espacoKids.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ResponsavelRepository responsavelRepository;

    public List<AlunoDetalhesDTO> listarTodosAlunos() {
        return alunoRepository.findAll().stream()
                .map(AlunoDetalhesDTO::new)
                .toList();
    }

    public AlunoDetalhesDTO cadastrarAluno(CadastroAlunoDTO dto) {
        var responsavel = responsavelRepository.findById(dto.responsavelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado"));

        var aluno = alunoRepository.save(new Aluno(dto, responsavel));
        return new AlunoDetalhesDTO(aluno);
    }

    public List<AlunoDetalhesDTO> buscarAlunosDoResponsavel(Long responsavelId) {
        if (!responsavelRepository.existsById(responsavelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado");
        }
        return alunoRepository.findByResponsavelId(responsavelId).stream()
                .map(AlunoDetalhesDTO::new)
                .toList();
    }

    public AlunoDetalhesDTO buscarDetalhesDoAluno(Long responsavelId, Long alunoId) {
        var aluno = alunoRepository.findByIdAndResponsavelId(alunoId, responsavelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        return new AlunoDetalhesDTO(aluno);
    }
}
