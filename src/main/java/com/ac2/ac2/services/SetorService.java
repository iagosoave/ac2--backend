package com.ac2.ac2.services;

import com.ac2.ac2.dtos.DadosSetorDTO;
import com.ac2.ac2.dtos.SetorDTO;

import java.util.List;

public interface SetorService {
    
    SetorDTO adicionar(SetorDTO dto);
    
    DadosSetorDTO buscarSetorPorId(Long idSetor);
    
    List<SetorDTO> listarTodos();
}