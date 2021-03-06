/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.view;

import com.agf.sinalescolar.dao.OcorrenciaDAO;
import com.agf.sinalescolar.model.Session;
import com.agf.sinalescolar.utils.CheckTimes;
import java.awt.Dimension;

/**
 *
 * @author Airan
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        obterLista();
        iniciarTarefa();
        this.setSize(new Dimension(1024, 750));
        this.setLocationRelativeTo(null);
    }
    
    public final void obterLista() {
        Session.getInstance().setListaOcorrencias(OcorrenciaDAO.getInstance().findSchedulesByDay());
    }
    
    public final void iniciarTarefa() {
        CheckTimes c = new CheckTimes(Session.getInstance().getListaOcorrencias());
        c.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        ifmUsuarios = new javax.swing.JMenuItem();
        ifmSetores = new javax.swing.JMenuItem();
        ifmEquipamentos = new javax.swing.JMenuItem();
        menuOcorrencias = new javax.swing.JMenu();
        ifmOcorrencias = new javax.swing.JMenuItem();
        ifmLogs = new javax.swing.JMenuItem();
        ifmAuditoria = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );

        menuCadastros.setText("Cadastros");

        ifmUsuarios.setText("Usuários");
        ifmUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifmUsuariosActionPerformed(evt);
            }
        });
        menuCadastros.add(ifmUsuarios);

        ifmSetores.setText("Setores");
        ifmSetores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifmSetoresActionPerformed(evt);
            }
        });
        menuCadastros.add(ifmSetores);

        ifmEquipamentos.setText("Equipamentos");
        ifmEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifmEquipamentosActionPerformed(evt);
            }
        });
        menuCadastros.add(ifmEquipamentos);

        jMenuBar1.add(menuCadastros);

        menuOcorrencias.setText("Controle");

        ifmOcorrencias.setText("Ocorrências");
        ifmOcorrencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifmOcorrenciasActionPerformed(evt);
            }
        });
        menuOcorrencias.add(ifmOcorrencias);

        ifmLogs.setText("Logs");
        ifmLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifmLogsActionPerformed(evt);
            }
        });
        menuOcorrencias.add(ifmLogs);

        ifmAuditoria.setText("Auditoria");
        ifmAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifmAuditoriaActionPerformed(evt);
            }
        });
        menuOcorrencias.add(ifmAuditoria);

        jMenuBar1.add(menuOcorrencias);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ifmUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifmUsuariosActionPerformed
        ifmUsuarios users = new ifmUsuarios();
        users.setVisible(true);
        jDesktopPane1.add(users);
    }//GEN-LAST:event_ifmUsuariosActionPerformed

    private void ifmSetoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifmSetoresActionPerformed
        ifmSetores sectors = new ifmSetores();
        sectors.setVisible(true);
        jDesktopPane1.add(sectors);
    }//GEN-LAST:event_ifmSetoresActionPerformed

    private void ifmEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifmEquipamentosActionPerformed
        ifmEquipamentos equipments = new ifmEquipamentos();
        equipments.setVisible(true);
        jDesktopPane1.add(equipments);
    }//GEN-LAST:event_ifmEquipamentosActionPerformed

    private void ifmOcorrenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifmOcorrenciasActionPerformed
        ifmOcorrencias ocurrencies = new ifmOcorrencias();
        ocurrencies.setVisible(true);
        jDesktopPane1.add(ocurrencies);
    }//GEN-LAST:event_ifmOcorrenciasActionPerformed

    private void ifmLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifmLogsActionPerformed
        ifmLogs logs = new ifmLogs();
        logs.setVisible(true);
        jDesktopPane1.add(logs);
    }//GEN-LAST:event_ifmLogsActionPerformed

    private void ifmAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifmAuditoriaActionPerformed
        ifmAuditoria adt = new ifmAuditoria();
        adt.setVisible(true);
        jDesktopPane1.add(adt);
    }//GEN-LAST:event_ifmAuditoriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ifmAuditoria;
    private javax.swing.JMenuItem ifmEquipamentos;
    private javax.swing.JMenuItem ifmLogs;
    private javax.swing.JMenuItem ifmOcorrencias;
    private javax.swing.JMenuItem ifmSetores;
    private javax.swing.JMenuItem ifmUsuarios;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuOcorrencias;
    // End of variables declaration//GEN-END:variables
}
