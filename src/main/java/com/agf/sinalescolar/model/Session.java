/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.model;

import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author airan.nascimento
 */
public class Session {
    private static Session instance = null;
    private Usuario u;
    private List<LocalTime> horariosOcorrencias;
    
    private Session() {
        
    }
    
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        
        return instance;
    }
    
    public Usuario getUsuario() {
        return u;
    }
    
    public void setUsuario(Usuario user) {
        this.u = user;
    }
    
    public List<LocalTime> getListaHorarios() {
        return horariosOcorrencias;
    }
    
    public void setListaHorarios(List<LocalTime> lista) {
        this.horariosOcorrencias = lista;
    }
    
}
