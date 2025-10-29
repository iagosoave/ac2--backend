package com.ac2.ac2.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosSetorDTO {
    private Long id;
    private String nome;
    private List<DadosFuncionarioDTO> funcionarios;
}