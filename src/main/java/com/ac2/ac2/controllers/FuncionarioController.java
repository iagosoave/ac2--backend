package com.ac2.ac2.controllers;


import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.FuncionarioDTO;
import com.ac2.ac2.models.Funcionario;
import com.ac2.ac2.services.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/funcionario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;
    
  
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario adicionar(@RequestBody FuncionarioDTO dto) {
        return funcionarioService.adicionar(dto);
    }
    
   
    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Long idFuncionario) {
        return funcionarioService.buscarProjetos(idFuncionario);
    }
}