package com.ac2.ac2.repositories;


import com.ac2.ac2.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
  
    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios f WHERE p.id = :id")
    Projeto findProjetoComFuncionarios(@Param("id") Long id);
    

    @Query("SELECT p FROM Projeto p WHERE p.dataInicio BETWEEN :dataInicio AND :dataFim")
    List<Projeto> findProjetosPorIntervaloData(
        @Param("dataInicio") LocalDate dataInicio, 
        @Param("dataFim") LocalDate dataFim
    );
}