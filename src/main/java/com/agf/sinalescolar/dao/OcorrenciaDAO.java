/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Ocorrencia;
import com.agf.sinalescolar.utils.JPAUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author airan.nascimento
 */
public class OcorrenciaDAO implements CRUD {
    private static OcorrenciaDAO instance = null;
    
    private OcorrenciaDAO() {
        
    }
    
    public static OcorrenciaDAO getInstance() {
        if (instance == null) {
            instance = new OcorrenciaDAO();
        }
        
        return instance;
    }
    
    @Override
    public void save(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Ocorrencia oc = (Ocorrencia) o;
        
        entityManager.getTransaction().begin();
        entityManager.persist(oc);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public void update(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Ocorrencia oc = (Ocorrencia) o;
        
        entityManager.getTransaction().begin();
        entityManager.merge(oc);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Ocorrencia oc = entityManager.find(Ocorrencia.class, id);
        
        entityManager.getTransaction().begin();
        entityManager.remove(oc);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public Object findById(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Ocorrencia oc = entityManager.find(Ocorrencia.class, id);
        
        entityManager.close();
        
        return oc;
    }

    @Override
    public List<Object> findAll() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Ocorrencia ORDER BY id");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
}
