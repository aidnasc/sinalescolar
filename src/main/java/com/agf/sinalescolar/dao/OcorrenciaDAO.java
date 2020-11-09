/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import com.agf.sinalescolar.model.Ocorrencia;
import com.agf.sinalescolar.model.Session;
import com.agf.sinalescolar.utils.JPAUtils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author airan.nascimento
 */
public class OcorrenciaDAO implements CRUD {
    private static OcorrenciaDAO instance = null;
    private final EquipamentoDAO ed = EquipamentoDAO.getInstance();
    private final DiaDAO dd = DiaDAO.getInstance();
    private final AuditoriaDAO ad = AuditoriaDAO.getInstance();
    
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
        
        ad.atualizarUsuarioResponsavel();
        Session.getInstance().setListaHorarios(findSchedulesByDay());
    }

    @Override
    public void update(Object o) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Ocorrencia oc = (Ocorrencia) o;
        
        entityManager.getTransaction().begin();
        entityManager.merge(oc);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
        ad.atualizarUsuarioResponsavel();
        Session.getInstance().setListaHorarios(findSchedulesByDay());
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Ocorrencia oc = entityManager.find(Ocorrencia.class, id);
        
        entityManager.getTransaction().begin();
        entityManager.remove(oc);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
        ad.atualizarUsuarioResponsavel();
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
    
    public List<Object> findAllByEquipment(String descricao) {
        int idequipamento = ed.findByDescription(descricao).getId();
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("FROM Ocorrencia o WHERE o.idequipamento = :idequipamento ORDER BY id");
        q.setParameter("idequipamento", idequipamento);
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public List<LocalTime> findSchedulesByDay() {        
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        Query q = entityManager.createQuery("SELECT o.hora_toque FROM Ocorrencia o WHERE o.iddia = :iddia");
        q.setParameter("iddia", dd.getIdOfTheDay());
        List lista = q.getResultList();
        
        entityManager.close();
        
        return lista;
    }
    
    public void popularTabelaOcorrencias(JTable tabela, String descricao) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "ID";
        cabecalho[1] = "Dia";
        cabecalho[2] = "Horário";
        cabecalho[3] = "Equipamento";
        
        List<Object> lista;
  
        if (descricao.length() > 0) {
            lista = this.findAllByEquipment(descricao);
        } else { 
            lista = this.findAll();
        }
        
        Ocorrencia o;
        LocalTime lt;
        dadosTabela = new Object[lista.size()][4]; 
        
        for (int i = 0; i < lista.size(); i++) {
            o = (Ocorrencia) lista.get(i);
            
            lt = o.getHora_toque();
            dadosTabela[i][0] = o.getId();
            dadosTabela[i][1] = dd.getDayById(o.getIddia());
            dadosTabela[i][2] = DateTimeFormatter.ofPattern("HH:mm:ss").format(lt);
            dadosTabela[i][3] = ed.findDescriptionById(o.getIdequipamento());
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
