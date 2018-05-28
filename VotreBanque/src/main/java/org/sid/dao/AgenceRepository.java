/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sid.dao;

import org.sid.entities.Agence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Brendev
 */
public interface AgenceRepository extends JpaRepository<Agence, Long>{
    @Query(value = "select * from agence", nativeQuery = true)
    @Transactional
    public Page<Agence> listAgence(Pageable pageable);
}
