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
    public SetorDTO adicionar(SetorDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do setor é obrigatório");
        }
        
        Setor setor = new Setor();
        setor.setNome(dto.getNome());
        
        Setor setorSalvo = setorRepository.save(setor);
        
        SetorDTO response = new SetorDTO();
        response.setId(setorSalvo.getId());
        response.setNome(setorSalvo.getNome());
        
        return response;
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
    public List<SetorDTO> listarTodos() {
        return setorRepository.findAll().stream()
                .map(s -> {
                    SetorDTO dto = new SetorDTO();
                    dto.setId(s.getId());
                    dto.setNome(s.getNome());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}