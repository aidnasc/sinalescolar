/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.dao;

import java.util.List;

/**
 *
 * @author Airan
 */
public interface CRUD {
    public void save (Object o);
    public void update (Object o);
    public void delete (int id);
    public Object findById (int id);
    public List<Object> findAll();
}
