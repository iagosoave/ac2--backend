package com.ac2.ac2.services;


import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.FuncionarioDTO;
import com.ac2.ac2.models.Funcionario;

import java.util.List;


public interface FuncionarioService {
    
  
    Funcionario adicionar(FuncionarioDTO dto);
    
   
    List<DadosProjetoDTO> buscarProjetos(Long idFuncionario);
}