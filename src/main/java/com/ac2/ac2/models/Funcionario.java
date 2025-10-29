package com.ac2.ac2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "funcionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 150, nullable = false)
    private String nome;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id", nullable = false)
    @JsonIgnoreProperties({"funcionarios"})
    private Setor setor;
    
    @ManyToMany(mappedBy = "funcionarios", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"funcionarios"})
    private List<Projeto> projetos = new ArrayList<>();
}