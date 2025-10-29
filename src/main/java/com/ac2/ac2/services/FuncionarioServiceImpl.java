package com.ac2.ac2.services;


import com.ac2.ac2.dtos.DadosFuncionarioDTO;
import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.FuncionarioDTO;
import com.ac2.ac2.dtos.SetorDTO;
import com.ac2.ac2.exceptions.RegraNegocioException;
import com.ac2.ac2.models.Funcionario;
import com.ac2.ac2.models.Setor;
import com.ac2.ac2.repositories.FuncionarioRepository;
import com.ac2.ac2.repositories.SetorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    
    @Override
    @Transactional
    public FuncionarioDTO adicionar(FuncionarioDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do funcionário é obrigatório");
        }
        
        if (dto.getSetorId() == null) {
            throw new RegraNegocioException("Setor é obrigatório");
        }
        
        Setor setor = setorRepository.findById(dto.getSetorId())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado com ID: " + dto.getSetorId()));
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setSetor(setor);
        
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        
        // Retornar DTO ao invés da entidade para evitar problemas de serialização
        FuncionarioDTO response = new FuncionarioDTO();
        response.setId(funcionarioSalvo.getId());
        response.setNome(funcionarioSalvo.getNome());
        response.setSetorId(funcionarioSalvo.getSetor().getId());
        
        return response;
    }
    
    @Override
    public List<FuncionarioDTO> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(f -> {
                    FuncionarioDTO dto = new FuncionarioDTO();
                    dto.setId(f.getId());
                    dto.setNome(f.getNome());
                    dto.setSetorId(f.getSetor().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DadosProjetoDTO> buscarProjetos(Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioComProjetos(idFuncionario);
        
        if (funcionario == null) {
            throw new RegraNegocioException("Funcionário não encontrado com ID: " + idFuncionario);
        }
        
        return funcionario.getProjetos().stream()
                .map(p -> DadosProjetoDTO.builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .descricao(p.getDescricao())
                    .dataInicio(p.getDataInicio())
                    .dataFim(p.getDataFim())
                    .funcionarios(
                        p.getFuncionarios().stream()
                            .map(f -> DadosFuncionarioDTO.builder()
                                .id(f.getId())
                                .nome(f.getNome())
                                .setor(SetorDTO.builder()
                                    .id(f.getSetor().getId())
                                    .nome(f.getSetor().getNome())
                                    .build())
                                .build())
                            .collect(Collectors.toList())
                    )
                    .build())
                .collect(Collectors.toList());
    }
}