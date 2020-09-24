/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Equipamento;
import com.agf.sinalescolar.utils.JPAUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author airan.nascimento
 */
public class EquipamentoDAO implements CRUD {
    private static EquipamentoDAO instance = null;
    private final SetorDAO sd = SetorDAO.getInstance();
    
    private EquipamentoDAO() {
        
    }
    
    public static EquipamentoDAO getInstance() {
        if (instance == null) {
            instance = new EquipamentoDAO();
        }
        
        return instance;
    }

    @Override
    public void save(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Equipamento e = (Equipamento) o;
        
        entityManager.getTransaction().begin();
        entityManager.persist(e);
        entityManager.getTransaction().commit();
        
        entityManager.close(); 
    }

    @Override
    public void update(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Equipamento e = (Equipamento) o;
        
        entityManager.getTransaction().begin();
        entityManager.merge(e);
        entityManager.getTransaction().commit();
        
        entityManager.close(); 
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Equipamento e = entityManager.find(Equipamento.class, id);
        
        entityManager.getTransaction().begin();
        entityManager.remove(e);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public Object findById(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Equipamento e = entityManager.find(Equipamento.class, id);
        
        entityManager.close();
        
        return e;
    }

    @Override
    public List<Object> findAll() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Equipamento ORDER BY id");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public List<Object> findAllByDescription(String descricao) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Equipamento e WHERE e.descricao LIKE :descricao ORDER BY id");
        q.setParameter("descricao", "%"+descricao+"%");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public Equipamento findByDescription(String descricao) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Equipamento e WHERE e.descricao = :descricao");
        q.setParameter("descricao", descricao);
        Equipamento e = (Equipamento) q.getResultList().get(0);
        
        entityManager.close();
        
        return e;
    }
    
    public void popularTabelaEquipamentos(JTable tabela, String descricao) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Descrição";
        cabecalho[1] = "Setor";
        
        List<Object> lista = new ArrayList<>();
  
        if (descricao.length() > 0) {
            lista = this.findAllByDescription(descricao);
        } else { 
            lista = this.findAll();
        }
        
        Equipamento e;
        dadosTabela = new Object[lista.size()][2]; 
        
        for (int i = 0; i < lista.size(); i++) {
            e = (Equipamento) lista.get(i);
            dadosTabela[i][0] = e.getDescricao();
            dadosTabela[i][1] = sd.findDescriptionById(e.getIdsetor());
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);
    }
}
