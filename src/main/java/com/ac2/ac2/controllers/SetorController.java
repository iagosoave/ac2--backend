package com.ac2.ac2.controllers;

import com.ac2.ac2.dtos.DadosSetorDTO;
import com.ac2.ac2.dtos.SetorDTO;
import com.ac2.ac2.services.SetorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/setor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SetorController {
    
    private final SetorService setorService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetorDTO adicionar(@RequestBody SetorDTO dto) {
        return setorService.adicionar(dto);
    }
    
    @GetMapping("/{idSetor}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Long idSetor) {
        return setorService.buscarSetorPorId(idSetor);
    }
    
    @GetMapping
    public List<SetorDTO> listarTodos() {
        return setorService.listarTodos();
    }
}