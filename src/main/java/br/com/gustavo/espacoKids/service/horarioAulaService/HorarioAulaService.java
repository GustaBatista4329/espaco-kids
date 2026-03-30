package br.com.gustavo.espacoKids.service.horarioAulaService;

import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.CadastroHorarioAulaDTO;
import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.CadastroResponsavelDTO;
import br.com.gustavo.espacoKids.domain.entity.aluno.Aluno;
import br.com.gustavo.espacoKids.domain.entity.horarioAula.HorarioAula;
import br.com.gustavo.espacoKids.repository.AlunoRepository;
import br.com.gustavo.espacoKids.repository.HorarioAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class HorarioAulaService {

    private final HorarioAulaRepository aulaRepositoryrepository;
    private final AlunoRepository alunoRepository;

    public CadastroHorarioAulaDTO cadastrarHorarioAula(CadastroHorarioAulaDTO cadastroHorarioAulaDTO){
        var alunoEncontrado = alunoRepository.findById(cadastroHorarioAulaDTO.alunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "aluno não encontrado"));

        var horarioAula = new HorarioAula(alunoEncontrado, cadastroHorarioAulaDTO);

        aulaRepositoryrepository.save(horarioAula);

        return cadastroHorarioAulaDTO;
    }

}
