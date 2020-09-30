/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Auditoria;
import com.agf.sinalescolar.model.Session;
import com.agf.sinalescolar.model.Usuario;
import com.agf.sinalescolar.utils.JPAUtils;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class AuditoriaDAO {
    private static AuditoriaDAO instance = null;
    private final UsuarioDAO ud = UsuarioDAO.getInstance();
    
    private AuditoriaDAO() {
        
    }
    
    public static AuditoriaDAO getInstance() {
        if (instance == null) {
            instance = new AuditoriaDAO();
        }
        
        return instance;
    }
    
    public void atualizarUsuarioResponsavel() {
        Auditoria a = (Auditoria) this.findLastOne();
        a.setIdusuario(Session.getInstance().getUsuario().getId());
        
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.merge(a);
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }

    public List<Object> findAll() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Auditoria ORDER BY id");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public List<Object> findAllByType(String tipo) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Auditoria a WHERE a.tipo = :tipo ORDER BY a.data, a.hora DESC");
        q.setParameter("tipo", tipo);
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    private Object findLastOne() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Auditoria a ORDER BY id DESC LIMIT 1");
        Auditoria a = (Auditoria) q.getResultList().get(0);
        
        entityManager.close();
        
        return a;
    }
    
    public void popularTabelaAuditoria(JTable tabela, String tipo) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Tipo";
        cabecalho[1] = "Anterior";
        cabecalho[2] = "Atual";
        cabecalho[3] = "Data";
        cabecalho[4] = "Hora";
        cabecalho[5] = "Usuário";  
        
       
        List<Object> lista;
  
        if (tipo.length() > 0) {
            lista = this.findAllByType(tipo);
        } else { 
            lista = this.findAll();
        }
        
        Auditoria a;
        Usuario u;
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        dadosTabela = new Object[lista.size()][6]; 
        
        for (int i = 0; i < lista.size(); i++) {
            a = (Auditoria) lista.get(i);
            u = (Usuario) ud.findById(a.getIdusuario());

            dadosTabela[i][0] = a.getTipo();
            dadosTabela[i][1] = a.getAnterior();
            dadosTabela[i][2] = a.getAtual();
            dadosTabela[i][3] = a.getData().format(formatoData);
            dadosTabela[i][4] = a.getHora().format(formatoHora);
            dadosTabela[i][5] = u.getNome();
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // Não permite seleção da tabela
        tabela.setEnabled(false);
    }
    
    public void popularComboBoxTiposAuditoria(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("Selecione um tipo...");
        combo.setSelectedIndex(0);
        
        String[] types = {"INSERT", "UPDATE", "DELETE"};
        
        for (String type : types) {
            combo.addItem(type);
        }
    }    
}
