/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sid.dao;

import org.sid.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Brendev
 */
public interface UserRepository extends JpaRepository<Users, String>{
    
}
