/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Usuario;
import com.agf.sinalescolar.utils.Encryption;
import com.agf.sinalescolar.utils.JPAUtils;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Airan
 */
public class UsuarioDAO implements CRUD { 
    private static UsuarioDAO instance = null;
    private final AuditoriaDAO ad = AuditoriaDAO.getInstance();
    
    private UsuarioDAO() {
        
    }
    
    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        
        return instance;
    }

    @Override
    public void save(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Usuario u = (Usuario) o;
        encriptarSenha(u);
        
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        
        entityManager.close();   
        
        ad.atualizarUsuarioResponsavel();
    }

    @Override
    public void update(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Usuario u = (Usuario) o;
        encriptarSenha(u);
        
        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
        ad.atualizarUsuarioResponsavel();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Usuario u = entityManager.find(Usuario.class, id);
        
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
        ad.atualizarUsuarioResponsavel();
    }

    @Override
    public Object findById(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Usuario u = entityManager.find(Usuario.class, id);
        
        entityManager.close();
        
        return u;
    }

    @Override
    public List<Object> findAll() {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Usuario ORDER BY id");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public List<Object> findAllByLogin(String login) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Usuario u WHERE u.login LIKE :login ORDER BY id");
        q.setParameter("login", "%"+login+"%");
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public Usuario findByLogin(String login) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Usuario u WHERE u.login = :login");
        q.setParameter("login", login);
        Usuario u = (Usuario) q.getResultList().get(0);
        
        entityManager.close();
        
        return u;
    }
    
    private void encriptarSenha(Usuario u) {
        try {
            u.setSenha(Encryption.hash(u.getSenha()+u.getSalt()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    public void popularTabelaUsuarios(JTable tabela, String login) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Nome";
        cabecalho[1] = "Login";
        
        List<Object> lista = new ArrayList<>();
  
        if (login.length() > 0) {
            lista = this.findAllByLogin(login);
        } else { 
            lista = this.findAll();
        }
        
        Usuario u;
        dadosTabela = new Object[lista.size()][2]; 
        
        for (int i = 0; i < lista.size(); i++) {
            u = (Usuario) lista.get(i);
            dadosTabela[i][0] = u.getNome();
            dadosTabela[i][1] = u.getLogin();
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
