/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.model;

import java.util.List;

/**
 *
 * @author airan.nascimento
 */
public class Session {
    private static Session instance = null;
    private Usuario u;
    private List<Ocorrencia> ocorrencias;
    
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
    
    public List<Ocorrencia> getListaOcorrencias() {
        return ocorrencias;
    }
    
    public void setListaOcorrencias(List<Ocorrencia> lista) {
        this.ocorrencias = lista;
    }
    
}
