/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime hora_toque;
    @Column
    private int iddia;
    @Column
    private int idequipamento;
    @Column
    private int idusuario;

    public Ocorrencia() {
        
    }

    public Ocorrencia(LocalDateTime hora_toque, int iddia, int idequipamento, int idusuario) {
        this.hora_toque = hora_toque;
        this.iddia = iddia;
        this.idequipamento = idequipamento;
        this.idusuario = idusuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHora_toque() {
        return hora_toque;
    }

    public void setHora_toque(LocalDateTime hora_toque) {
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

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
}
