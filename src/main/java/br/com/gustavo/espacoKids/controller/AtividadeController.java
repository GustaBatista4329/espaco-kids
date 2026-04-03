package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.AtividadeAlunoDTO;
import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.AtribuirAtividadeDTO;
import br.com.gustavo.espacoKids.domain.dto.atividadeDTO.BancoAtividadeDTO;
import br.com.gustavo.espacoKids.service.atividadeService.AtividadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/atividade")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService service;
    private final Path atividadesUploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<BancoAtividadeDTO> upload(@RequestParam("titulo") String titulo,
                                                    @RequestParam(value = "descricao", required = false) String descricao,
                                                    @RequestParam("arquivo") MultipartFile arquivo) {
        var dto = service.uploadAtividade(titulo, descricao, arquivo);
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
    public ResponseEntity<List<AtividadeAlunoDTO>> listarAtividadesDoAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(service.listarAtividadesDoAluno(alunoId));
    }

    @GetMapping("/download/{nomeArquivo}")
    public ResponseEntity<Resource> download(@PathVariable String nomeArquivo) throws MalformedURLException {
        Path arquivo = atividadesUploadPath.resolve(nomeArquivo).normalize();
        Resource resource = new UrlResource(arquivo.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivo + "\"")
                .body(resource);
    }
}
