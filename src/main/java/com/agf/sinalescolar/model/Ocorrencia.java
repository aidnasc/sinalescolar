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
    private LocalDateTime hora_inicio;
    @Column
    private LocalDateTime hora_fim;
    @Column
    private int intervalo;
    @Column
    private LocalDateTime hora_toque;
    @Column
    private int idusuario;

    public Ocorrencia() {
        
    }

    public Ocorrencia(LocalDateTime hora_inicio, LocalDateTime hora_fim, int intervalo, LocalDateTime hora_toque, int idusuario) {
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.intervalo = intervalo;
        this.hora_toque = hora_toque;
        this.idusuario = idusuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalDateTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalDateTime getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(LocalDateTime hora_fim) {
        this.hora_fim = hora_fim;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public LocalDateTime getHora_toque() {
        return hora_toque;
    }

    public void setHora_toque(LocalDateTime hora_toque) {
        this.hora_toque = hora_toque;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
}
