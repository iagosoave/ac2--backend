package com.ac2.ac2.services;


import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.ProjetoDTO;
import com.ac2.ac2.models.Projeto;

import java.util.List;


public interface ProjetoService {
    
   
    Projeto adicionar(ProjetoDTO dto);
    
  
    DadosProjetoDTO buscarProjetoPorId(Long id);
    
  
    void vincularFuncionario(Long idProjeto, Long idFuncionario);
    
 
    List<DadosProjetoDTO> listarTodos();
}