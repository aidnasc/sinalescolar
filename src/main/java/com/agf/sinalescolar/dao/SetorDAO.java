/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Setor;
import com.agf.sinalescolar.utils.JPAUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author airan.nascimento
 */
public class SetorDAO implements CRUD {
    private static SetorDAO instance = null;
    
    private SetorDAO() {
        
    }
    
    public static SetorDAO getInstance() {
        if (instance == null) {
            instance = new SetorDAO();
        }
        
        return instance;
    }

    @Override
    public void save(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Setor s = (Setor) o;
        
        entityManager.getTransaction().begin();
        entityManager.persist(s);
        entityManager.getTransaction().commit();
        
        entityManager.close(); 
    }

    @Override
    public void update(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Setor s = (Setor) o;
        
        entityManager.getTransaction().begin();
        entityManager.merge(s);
        entityManager.getTransaction().commit();
        
        entityManager.close(); 
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Setor s = entityManager.find(Setor.class, id);
        
        entityManager.getTransaction().begin();
        entityManager.remove(s);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    @Override
    public Object findById(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Setor s = entityManager.find(Setor.class, id);
        
        entityManager.close();
        
        return s;
    }

    @Override
    public List<Object> findAll() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Setor ORDER BY id");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public String findDescriptionById(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Setor s = entityManager.find(Setor.class, id);
        
        entityManager.close();
        
        return s.getDescricao();
    }
    
    public List<Object> findAllByDescription(String descricao) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Setor s WHERE s.descricao LIKE :descricao ORDER BY id");
        q.setParameter("descricao", "%"+descricao+"%");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public Setor findByDescription(String descricao) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Setor s WHERE s.descricao = :descricao");
        q.setParameter("descricao", descricao);
        Setor s = (Setor) q.getResultList().get(0);
        
        entityManager.close();
        
        return s;
    }
    
    public void popularTabelaSetores(JTable tabela, String descricao) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "ID";
        cabecalho[1] = "Descrição";
        
        List<Object> lista = new ArrayList<>();
  
        if (descricao.length() > 0) {
            lista = this.findAllByDescription(descricao);
        } else { 
            lista = this.findAll();
        }
        
        Setor s;
        dadosTabela = new Object[lista.size()][2]; 
        
        for (int i = 0; i < lista.size(); i++) {
            s = (Setor) lista.get(i);
            dadosTabela[i][0] = s.getId();
            dadosTabela[i][1] = s.getDescricao();
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
    
    public void popularComboBoxSetores(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("Selecione um setor...");
        combo.setSelectedIndex(0);
        
        List<Object> lista = this.findAll();
        
        for (int i = 0; i < lista.size(); i++) {
            Setor s = (Setor) lista.get(i);
            combo.addItem(s.getDescricao());
        }
    }
    
}
