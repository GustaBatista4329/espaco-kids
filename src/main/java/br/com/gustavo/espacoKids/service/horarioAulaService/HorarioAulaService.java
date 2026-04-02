package br.com.gustavo.espacoKids.service.horarioAulaService;

import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.CadastroHorarioAulaDTO;
import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.DetalhesHorarioAulaDTO;
import br.com.gustavo.espacoKids.domain.entity.horarioAula.HorarioAula;
import br.com.gustavo.espacoKids.repository.AlunoRepository;
import br.com.gustavo.espacoKids.repository.HorarioAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioAulaService {

    private final HorarioAulaRepository horarioAulaRepository;
    private final AlunoRepository alunoRepository;

    @Transactional
    public DetalhesHorarioAulaDTO cadastrarHorarioAula(CadastroHorarioAulaDTO dto) {
        var aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        horarioAulaRepository.save(new HorarioAula(aluno, dto));
        return new DetalhesHorarioAulaDTO(
                aluno.getNome(),
                aluno.getResponsavel().getUsuarioId().getNome(),
                dto.diaSemana(),
                dto.horaInicio(),
                dto.horaFim()
        );
    }

    public List<DetalhesHorarioAulaDTO> buscarTodosHorariosAula() {
        var horariosAula = horarioAulaRepository.buscarTodosHorarios();

        return horariosAula;
    }

    public List<DetalhesHorarioAulaDTO> buscarHorariosPorAluno(Long id) {
        var horariosAula = horarioAulaRepository.buscarHorariosPorAlunoId(id);

        return horariosAula;
    }

    public List<DetalhesHorarioAulaDTO> buscarHorariosPorResponsavel(Long id) {

        var horariosAula = horarioAulaRepository.buscarHorariosPorResponsavelId(id);

        return horariosAula;

    }
}
