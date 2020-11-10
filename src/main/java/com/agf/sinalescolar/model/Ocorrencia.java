/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Airan
 */
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String hora_toque;
    @Column
    private int iddia;
    @Column
    private int idequipamento;

    public Ocorrencia() {
        
    }

    public Ocorrencia(String hora_toque, int iddia, int idequipamento) {
        this.hora_toque = hora_toque;
        this.iddia = iddia;
        this.idequipamento = idequipamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHora_toque() {
        return hora_toque;
    }

    public void setHora_toque(String hora_toque) {
        this.hora_toque = hora_toque;
    }

    public int getIddia() {
        return iddia;
    }

    public void setIddia(int iddia) {
        this.iddia = iddia;
    }
    
    public int getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(int idequipamento) {
        this.idequipamento = idequipamento;
    }
    
}
