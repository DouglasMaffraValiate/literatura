package com.douglasmv.literatura.repository;

import com.douglasmv.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.dataNascimento <= :anoNascimento AND a.dataObto >= :anoObto")
    List<Autor> buscarAutoresVivosDataData(@Param("anoNascimento") Integer anoNascimento,
                                           @Param("anoObto") Integer anoObto);
}
