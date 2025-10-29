package com.ac2.ac2.services;


import com.ac2.ac2.dtos.DadosFuncionarioDTO;
import com.ac2.ac2.dtos.DadosSetorDTO;
import com.ac2.ac2.dtos.SetorDTO;
import com.ac2.ac2.exceptions.RegraNegocioException;
import com.ac2.ac2.models.Setor;
import com.ac2.ac2.repositories.SetorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SetorServiceImpl implements SetorService {
    
    private final SetorRepository setorRepository;
    
    @Override
    @Transactional
    public Setor adicionar(SetorDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do setor é obrigatório");
        }
        
        Setor setor = new Setor();
        setor.setNome(dto.getNome());
        
        return setorRepository.save(setor);
    }
    
    @Override
    public DadosSetorDTO buscarSetorPorId(Long idSetor) {
        Setor setor = setorRepository.findSetorComFuncionarios(idSetor);
        
        if (setor == null) {
            throw new RegraNegocioException("Setor não encontrado com ID: " + idSetor);
        }
        
        return DadosSetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .funcionarios(
                    setor.getFuncionarios().stream()
                        .map(f -> DadosFuncionarioDTO.builder()
                            .id(f.getId())
                            .nome(f.getNome())
                            .email(f.getEmail())
                            .setor(SetorDTO.builder()
                                .id(setor.getId())
                                .nome(setor.getNome())
                                .build())
                            .build())
                        .collect(Collectors.toList())
                )
                .build();
    }
    
    @Override
    public List<DadosSetorDTO> listarTodos() {
        return setorRepository.findAll().stream()
                .map(s -> DadosSetorDTO.builder()
                    .id(s.getId())
                    .nome(s.getNome())
                    .funcionarios(
                        s.getFuncionarios().stream()
                            .map(f -> DadosFuncionarioDTO.builder()
                                .id(f.getId())
                                .nome(f.getNome())
                                .email(f.getEmail())
                                .setor(SetorDTO.builder()
                                    .id(s.getId())
                                    .nome(s.getNome())
                                    .build())
                                .build())
                            .collect(Collectors.toList())
                    )
                    .build())
                .collect(Collectors.toList());
    }
}