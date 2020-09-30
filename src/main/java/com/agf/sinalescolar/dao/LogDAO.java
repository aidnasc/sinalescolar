/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Log;
import com.agf.sinalescolar.utils.JPAUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class LogDAO {
    private static LogDAO instance = null;
    
    private LogDAO() {
        
    }
    
    public static LogDAO getInstance() {
        if (instance == null) {
            instance = new LogDAO();
        }
        
        return instance;
    }
    
    public List<Object> getAllLogs() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Log ORDER BY datahora DESC");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public List<Object> getAllLogsByLevel(String level) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Log l WHERE l.nivel=:level ORDER BY datahora DESC");
        q.setParameter("level", level);
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public void popularTabelaLogs(JTable tabela, String level) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Data e Hora";
        cabecalho[1] = "Logger";
        cabecalho[2] = "Nível";
        
        List<Object> lista;
  
        if (level.length() > 0) {
            lista = this.getAllLogsByLevel(level);
        } else { 
            lista = this.getAllLogs();
        }
        
        Log l;
        LocalDateTime ldt;
        dadosTabela = new Object[lista.size()][3]; 
        
        for (int i = 0; i < lista.size(); i++) {
            l = (Log) lista.get(i);
            
            ldt = l.getDatahora().toLocalDateTime();
            dadosTabela[i][0] = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(ldt);
            dadosTabela[i][1] = l.getLogger();
            dadosTabela[i][2] = l.getNivel();
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
    
    public void popularComboBoxNiveisLogs(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("Selecione um nível...");
        combo.setSelectedIndex(0);
        
        String[] levels = {"DEBUG", "INFO", "WARN", "ERROR", "FATAL"};
        
        for (String level : levels) {
            combo.addItem(level);
        }
    }
}
