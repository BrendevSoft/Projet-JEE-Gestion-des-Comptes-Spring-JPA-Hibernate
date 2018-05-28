package org.sid.dao;

import org.sid.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select * from client", nativeQuery = true)
    @Transactional
    public Page<Client> listClient(Pageable pageable);
}
