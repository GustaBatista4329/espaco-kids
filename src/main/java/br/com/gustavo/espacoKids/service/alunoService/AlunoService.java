package br.com.gustavo.espacoKids.service.alunoService;

import br.com.gustavo.espacoKids.dto.alunoDTO.AlunoDetalhesDTO;
import br.com.gustavo.espacoKids.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.entity.aluno.Aluno;
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

    public CadastroAlunoDTO cadastrarAlunoDTO(CadastroAlunoDTO cadastroAlunoDTO){
        var responsavel = responsavelRepository.findById(cadastroAlunoDTO.responsavelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse responsável não está cadastrado"));

        var aluno = new Aluno(cadastroAlunoDTO, responsavel);
        alunoRepository.save(aluno);

        return cadastroAlunoDTO;
    }

    public List<AlunoDetalhesDTO> buscarAlunosDoResponsavel(Long responsavelId){
        var alunos = alunoRepository.findByResponsavelId(responsavelId);

        if(alunos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse responsável não está cadastrado");
        }

        List<AlunoDetalhesDTO> alunosDetalhes = alunos.stream()
                .map(aluno -> new AlunoDetalhesDTO(aluno))
                .toList();


        return alunosDetalhes;
    }

    public AlunoDetalhesDTO buscarDetalhesDoAluno(Long responsavelId, Long alunoId){
        var alunoOptional = alunoRepository.findByIdAndResponsavelId(alunoId, responsavelId);

        if(alunoOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não existe");
        }

        var aluno = alunoOptional.get();

        var alunoDetalhamento = new AlunoDetalhesDTO(aluno);

        return alunoDetalhamento;

    }

}
