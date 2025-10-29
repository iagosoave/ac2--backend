package com.ac2.ac2.services;


import com.ac2.ac2.dtos.DadosFuncionarioDTO;
import com.ac2.ac2.dtos.DadosProjetoDTO;
import com.ac2.ac2.dtos.ProjetoDTO;
import com.ac2.ac2.dtos.SetorDTO;
import com.ac2.ac2.exceptions.RegraNegocioException;
import com.ac2.ac2.models.Funcionario;
import com.ac2.ac2.models.Projeto;
import com.ac2.ac2.repositories.FuncionarioRepository;
import com.ac2.ac2.repositories.ProjetoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    
    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;
    
    @Override
    @Transactional
    public DadosProjetoDTO adicionar(ProjetoDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do projeto é obrigatório");
        }
        
        if (dto.getDataInicio() == null) {
            throw new RegraNegocioException("Data de início é obrigatória");
        }
        
        if (dto.getDataFim() == null) {
            throw new RegraNegocioException("Data de fim é obrigatória");
        }
        
        if (dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new RegraNegocioException("Data de fim deve ser posterior à data de início");
        }
        
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        
        Projeto projetoSalvo = projetoRepository.save(projeto);
        
        return DadosProjetoDTO.builder()
                .id(projetoSalvo.getId())
                .nome(projetoSalvo.getNome())
                .descricao(projetoSalvo.getDescricao())
                .dataInicio(projetoSalvo.getDataInicio())
                .dataFim(projetoSalvo.getDataFim())
                .funcionarios(new ArrayList<>())
                .build();
    }
    
    @Override
    public DadosProjetoDTO buscarProjetoPorId(Long id) {
        Projeto projeto = projetoRepository.findProjetoComFuncionarios(id);
        
        if (projeto == null) {
            throw new RegraNegocioException("Projeto não encontrado com ID: " + id);
        }
        
        return DadosProjetoDTO.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarios(
                    projeto.getFuncionarios().stream()
                        .map(f -> DadosFuncionarioDTO.builder()
                            .id(f.getId())
                            .nome(f.getNome())
                            .email(f.getEmail())
                            .setor(SetorDTO.builder()
                                .id(f.getSetor().getId())
                                .nome(f.getSetor().getNome())
                                .build())
                            .build())
                        .collect(Collectors.toList())
                )
                .build();
    }
    
    @Override
    @Transactional
    public void vincularFuncionario(Long idProjeto, Long idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com ID: " + idProjeto));
        
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado com ID: " + idFuncionario));
        
        if (projeto.getFuncionarios().contains(funcionario)) {
            throw new RegraNegocioException("Funcionário já vinculado a este projeto");
        }
        
        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }
    
    @Override
    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.findAll().stream()
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
                                .email(f.getEmail())
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