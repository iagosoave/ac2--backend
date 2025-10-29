package com.ac2.ac2.services;


import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.FuncionarioDTO;

import java.util.List;


public interface FuncionarioService {
    
  
    FuncionarioDTO adicionar(FuncionarioDTO dto);
    
    List<FuncionarioDTO> listarTodos();
   
    List<DadosProjetoDTO> buscarProjetos(Long idFuncionario);
}