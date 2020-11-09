/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.utils.JPAUtils;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author airan.nascimento
 */
public class DiaDAO {
    private static DiaDAO instance = null;
    private int iddia;
    
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

        Query q = entityManager.createQuery("SELECT d.dia FROM Dia d WHERE d.id=:iddia ORDER BY id");
        q.setParameter("iddia", iddia);
        String dia = q.getResultList().get(0).toString();
        
        entityManager.close();
        
        return dia;
    }
    
    public int getIdOfTheDay() {
        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 1: // Começa no domingo
                iddia = 7;
                break;
            case 2:
                iddia = 1;
                break;
            case 3:
                iddia = 2;
                break;
            case 4:
                iddia = 3;
                break;
            case 5:
                iddia = 4;
                break;
            case 6:
                iddia = 5;
                break;
            case 7:
                iddia = 6;
                break;
            default:
                iddia = 1;
                break;
        }
        
        return iddia;
    }
}
