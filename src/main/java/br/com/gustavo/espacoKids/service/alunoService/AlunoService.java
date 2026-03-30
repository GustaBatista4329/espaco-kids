package br.com.gustavo.espacoKids.service.alunoService;

import br.com.gustavo.espacoKids.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.entity.aluno.Aluno;
import br.com.gustavo.espacoKids.repository.AlunoRepository;
import br.com.gustavo.espacoKids.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

}
