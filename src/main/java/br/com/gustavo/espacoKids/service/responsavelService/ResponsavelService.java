package br.com.gustavo.espacoKids.service.responsavelService;

import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.CadastroResponsavelDTO;
import br.com.gustavo.espacoKids.domain.entity.responsavel.Responsavel;
import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.repository.ResponsavelRepository;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final UsuarioRepository usuarioRepository;
    private final ResponsavelRepository responsavelRepository;

    public CadastroResponsavelDTO cadastrarResponsavel(CadastroResponsavelDTO cadastroResponsavelDTO){
        Usuario usuario = usuarioRepository.findById(cadastroResponsavelDTO.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if(!usuario.getPerfil().equals(Perfil.RESPONSAVEL)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Esse perfil não é de um responsável");
        }

        var responsavel = new Responsavel(cadastroResponsavelDTO, usuario);
        responsavelRepository.save(responsavel);
        return cadastroResponsavelDTO;

    }



}
