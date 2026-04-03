package br.com.gustavo.espacoKids.service.responsavelService;

import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.CadastroResponsavelDTO;
import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.ResponsavelDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.UsuarioDisponivelDTO;
import br.com.gustavo.espacoKids.domain.entity.responsavel.Responsavel;
import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.repository.ResponsavelRepository;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final UsuarioRepository usuarioRepository;
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelDetalhesDTO cadastrarResponsavel(CadastroResponsavelDTO dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!usuario.getPerfil().equals(Perfil.RESPONSAVEL)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Esse usuário não tem perfil de responsável");
        }

        var responsavel = responsavelRepository.save(new Responsavel(dto, usuario));
        return new ResponsavelDetalhesDTO(responsavel);
    }

    public List<ResponsavelDetalhesDTO> listarTodosResponsaveis() {
        return responsavelRepository.findAll().stream()
                .map(ResponsavelDetalhesDTO::new)
                .toList();
    }
    public List<UsuarioDisponivelDTO> listarUsuariosDisponiveis() {
        return usuarioRepository.findUsuariosResponsaveisSemVinculo().stream()
                .map(UsuarioDisponivelDTO::new)
                .toList();
    }
}
