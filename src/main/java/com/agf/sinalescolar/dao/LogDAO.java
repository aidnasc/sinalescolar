/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Log;
import com.agf.sinalescolar.utils.JPAUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author airan.nascimento
 */
public class LogDAO implements CRUD {
    private static LogDAO instance = null;
    
    private LogDAO() {
        
    }
    
    public static LogDAO getInstance() {
        if (instance == null) {
            instance = new LogDAO();
        }
        
        return instance;
    }
    
    @Override
    public void save(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Log l = (Log) o;
        
        entityManager.getTransaction().begin();
        entityManager.persist(l);
        entityManager.getTransaction().commit();
        
        entityManager.close(); 
    }

    @Override
    public void update(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Log l = (Log) o;
        
        entityManager.getTransaction().begin();
        entityManager.merge(l);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Log l = entityManager.find(Log.class, id);
        
        entityManager.getTransaction().begin();
        entityManager.remove(l);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public Object findById(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Log l = entityManager.find(Log.class, id);
        
        entityManager.close();
        
        return l;
    }

    @Override
    public List<Object> findAll() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Log ORDER BY id");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
        
}
