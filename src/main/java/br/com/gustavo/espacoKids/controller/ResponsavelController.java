package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.entity.responsavel.Responsavel;
import br.com.gustavo.espacoKids.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("responsavel") @RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelRepository repository;

    @GetMapping
    public List<Responsavel> listarTodosResponsaveis(){
        return repository.findAll();
    }

}
