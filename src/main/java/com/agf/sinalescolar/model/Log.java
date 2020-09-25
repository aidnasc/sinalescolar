/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author airan.nascimento
 */
@Entity
@Table(name = "log")
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Timestamp datahora;
    @Column
    private String logger;
    @Column
    private String nivel;
    @Column
    private String mensagem;

    public Integer getId() {
        return id;
    }

    public Timestamp getDatahora() {
        return datahora;
    }

    public String getLogger() {
        return logger;
    }

    public String getNivel() {
        return nivel;
    }

    public String getMensagem() {
        return mensagem;
    }
    
}
