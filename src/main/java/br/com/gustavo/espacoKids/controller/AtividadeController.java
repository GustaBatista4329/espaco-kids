package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.AtividadeAlunoDTO;
import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.AtribuirAtividadeDTO;
import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.BancoAtividadeDTO;
import br.com.gustavo.espacoKids.service.atividadeService.AtividadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gustavo.espacoKids.domain.entity.atividade.Categoria;
import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.repository.AlunoRepository;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/atividade")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService service;
    private final Path atividadesUploadPath;
    private final AlunoRepository alunoRepository;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<BancoAtividadeDTO> upload(@RequestParam("titulo") String titulo,
                                                    @RequestParam(value = "descricao", required = false) String descricao,
                                                    @RequestParam("categoria") String categoria,
                                                    @RequestParam("arquivo") MultipartFile arquivo) {
        var dto = service.uploadAtividade(titulo, descricao, categoria, arquivo);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/atividade/banco/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/banco")
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<List<BancoAtividadeDTO>> listarBanco() {
        return ResponseEntity.ok(service.listarBanco());
    }

    @DeleteMapping("/banco/{id}")
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<Void> excluirDoBanco(@PathVariable Long id) {
        service.excluirDoBanco(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atribuir")
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<AtividadeAlunoDTO> atribuir(@RequestBody @Valid AtribuirAtividadeDTO dto) {
        var atividadeAluno = service.atribuirParaAluno(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/atividade/atribuicao/{id}")
                .buildAndExpand(atividadeAluno.id())
                .toUri();
        return ResponseEntity.created(uri).body(atividadeAluno);
    }

    @DeleteMapping("/atribuicao/{id}")
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<Void> removerAtribuicao(@PathVariable Long id) {
        service.removerAtribuicao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<AtividadeAlunoDTO>> listarAtividadesDoAluno(
            @PathVariable Long alunoId,
            @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfil() == Perfil.RESPONSAVEL
                && alunoRepository.findByIdAndResponsavelId(alunoId, usuario.getResponsavel().getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.listarAtividadesDoAluno(alunoId));
    }

    @GetMapping("/categorias")
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<List<Map<String, String>>> listarCategorias() {
        var categorias = Arrays.stream(Categoria.values())
                .map(c -> Map.of("value", c.name(), "label", c.getDescricao()))
                .toList();
        return ResponseEntity.ok(categorias);
    }

    private static final Map<String, MediaType> MEDIA_TYPES = Map.of(
            "pdf", MediaType.APPLICATION_PDF,
            "doc", MediaType.valueOf("application/msword"),
            "docx", MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
            "pptx", MediaType.valueOf("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
            "jpeg", MediaType.IMAGE_JPEG,
            "jpg", MediaType.IMAGE_JPEG,
            "png", MediaType.IMAGE_PNG
    );

    @GetMapping("/download/{nomeArquivo}")
    public ResponseEntity<Resource> download(@PathVariable String nomeArquivo) throws MalformedURLException {
        Path arquivo = atividadesUploadPath.resolve(nomeArquivo).normalize();

        if (!arquivo.startsWith(atividadesUploadPath)) {
            return ResponseEntity.badRequest().build();
        }

        Resource resource = new UrlResource(arquivo.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String ext = nomeArquivo.contains(".")
                ? nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1).toLowerCase()
                : "pdf";
        MediaType mediaType = MEDIA_TYPES.getOrDefault(ext, MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(nomeArquivo, java.nio.charset.StandardCharsets.UTF_8)
                                .build()
                                .toString())
                .body(resource);
    }
}
