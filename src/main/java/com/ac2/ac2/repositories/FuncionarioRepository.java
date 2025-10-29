package com.ac2.ac2.repositories;


import com.ac2.ac2.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
   
    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.projetos p WHERE f.id = :id")
    Funcionario findFuncionarioComProjetos(@Param("id") Long id);
}