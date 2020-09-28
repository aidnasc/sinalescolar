/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.utils.JPAUtils;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author airan.nascimento
 */
public class DiaDAO {
    private static DiaDAO instance = null;
    
    private DiaDAO() {
        
    }
    
    public static DiaDAO getInstance() {
        if (instance == null) {
            instance = new DiaDAO();
        } 
        
        return instance;
    }
    
    public String getDayById(int iddia) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("SELECT d.descricao FROM Dia_da_semana d WHERE d.id=:iddia ORDER BY id");
        q.setParameter("iddia", iddia);
        String dia = q.getResultList().get(0).toString();
        
        entityManager.close();
        
        return dia;
    }
}
