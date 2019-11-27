/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Rappel.Repository;

import com.example.Rappel.Model.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sniper Elite
 */
public interface AdminRepository extends CrudRepository<Admin, Long> {
    
}
