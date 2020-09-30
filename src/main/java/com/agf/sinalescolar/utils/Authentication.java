/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.utils;

import com.agf.sinalescolar.model.Session;
import com.agf.sinalescolar.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Airan
 */
public class Authentication {
    public static boolean autenticaUsuario(String login, String senha, int salt) {
        boolean autenticado = false;

        try {
            String hash = Encryption.hash(senha + salt);  
            
            EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();
            
            Query q = entityManager.createQuery("FROM Usuario u WHERE u.login=:login AND u.senha=:senha");
            q.setParameter("login", login);
            q.setParameter("senha", hash);
            
            if (!q.getResultList().isEmpty()) {
                Usuario uLogado = (Usuario) q.getResultList().get(0);
                Session.getInstance().setUsuario(uLogado);
                autenticado = true;             
            }
            
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return autenticado;
    }

}
