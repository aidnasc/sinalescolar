/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.utils;

import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Airan
 */
public class Salt {
    public static int geraSalt() {
        return new Random().nextInt();
    }

    public static int consultaSalt(String login) {
        int salt = 0;

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();
            
        Query q = entityManager.createQuery("SELECT u.salt FROM Usuario u WHERE u.login=:login");
        q.setParameter("login", login);
        List lista = q.getResultList();
        
        if (!lista.isEmpty()) {
            salt = Integer.parseInt(lista.get(0).toString());
        }
        
        return salt;
    }
}
