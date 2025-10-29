package com.ac2.ac2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "projeto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 200, nullable = false)
    private String nome;
    
    @Column(length = 500)
    private String descricao;
    
    @Column(nullable = false)
    private LocalDate dataInicio;
    
    @Column(nullable = false)
    private LocalDate dataFim;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "projeto_funcionario",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    @JsonIgnoreProperties({"projetos", "setor"})
    private List<Funcionario> funcionarios = new ArrayList<>();
}