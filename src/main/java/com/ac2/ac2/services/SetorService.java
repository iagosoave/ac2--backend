package com.ac2.ac2.services;


import java.util.List;

import com.ac2.ac2.dtos.DadosSetorDTO;
import com.ac2.ac2.dtos.SetorDTO;
import com.ac2.ac2.models.Setor;


public interface SetorService {
    
   
    Setor adicionar(SetorDTO dto);
    
 
    DadosSetorDTO buscarSetorPorId(Long idSetor);
    
    List<DadosSetorDTO> listarTodos();
}