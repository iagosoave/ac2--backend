package com.ac2.ac2.repositories;


import com.ac2.ac2.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    
 
    @Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios f WHERE s.id = :id")
    Setor findSetorComFuncionarios(@Param("id") Long id);
}