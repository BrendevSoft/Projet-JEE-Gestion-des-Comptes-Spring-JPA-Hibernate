package org.sid.dao;

import java.util.Date;
import org.sid.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CompteRepository extends JpaRepository<Compte, String> {

    @Modifying
    @Query(value = "insert into compte(code_compte,type_cpte,date_creation,solde,taux,decouvert,code,id_agence,id_personnel) VALUE ( :code_compte,:type_cpte,:date_creation,:solde,:taux,:decouvert,:code,:id_agence,:id_personnel)", nativeQuery = true)
    @Transactional
    public void insertcompte(@Param("code_compte") String code_compte, @Param("type_cpte") String type_cpte,
            @Param("date_creation") Date date_creation, @Param("solde") double solde, @Param("taux") double taux,
            @Param("decouvert") double decouvert, @Param("code") Long code, @Param("id_agence") Long id_agence,
            @Param("id_personnel") Long id_personnel);
}
