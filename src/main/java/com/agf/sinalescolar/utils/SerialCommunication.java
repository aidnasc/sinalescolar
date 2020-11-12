/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.utils;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Airan
 */
public class SerialCommunication {

    private static final int BAUD_RATE = 9600;
    private static final int DATA_BITS = 8;
    private static final int STOP_BITS = 1;
    private static final int PARITY = 0;
    private static final String SERIAL_PORT = "COM6";

    public static void send(String dados) throws SerialPortException, InterruptedException {
        SerialPort serialPort = new SerialPort(SERIAL_PORT);
        
        System.out.print("Preparando serial para enviar  ");
        System.out.println(">" + dados + "<");
        
        if (serialPort.openPort()) {
            System.out.println("Porta " + SERIAL_PORT + " aberta");
            Thread.sleep(3000);
        } else {
            System.out.println("Ocorreu um erro ao abrir a porta " + SERIAL_PORT);
        }
        
        serialPort.setParams(BAUD_RATE, DATA_BITS, STOP_BITS, PARITY, false, false);
        
        if (serialPort.writeString(dados)) {
            System.out.println("Dados >" + dados + "< enviados!");
            Thread.sleep(2000);
        } else {
            System.out.println("Dados não enviados");
        }
        
        if (serialPort.closePort()) {
            System.out.println("Porta " + SERIAL_PORT + " fechada");
        } else {
            System.out.println("Ocorreu um erro ao fechar a porta" + SERIAL_PORT);
        }

    }
}
