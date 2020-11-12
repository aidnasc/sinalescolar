/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.view;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Airan
 */
public class Main {
    public static void main(String[] args) throws SerialPortException {
        SerialPort sp = new SerialPort("COM6");
        if (sp.isOpened()) {
            sp.closePort();
        }
        Login l = new Login();
        l.setVisible(true);
    }
}
