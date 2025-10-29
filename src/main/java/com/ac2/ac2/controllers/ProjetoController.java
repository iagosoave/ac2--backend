package com.ac2.ac2.controllers;


import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.ProjetoDTO;
import com.ac2.ac2.services.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projeto")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjetoController {
    
    private final ProjetoService projetoService;
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosProjetoDTO adicionar(@RequestBody ProjetoDTO dto) {
        return projetoService.adicionar(dto);
    }
  
    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Long id) {
        return projetoService.buscarProjetoPorId(id);
    }
    
   
    @PostMapping("/{idProjeto}/vincular/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincularFuncionario(
            @PathVariable Long idProjeto, 
            @PathVariable Long idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }
  
    @GetMapping
    public List<DadosProjetoDTO> listarTodos() {
        return projetoService.listarTodos();
    }
}