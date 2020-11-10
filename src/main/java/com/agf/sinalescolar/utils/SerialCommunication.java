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
    private static final int BAUD_RATE = 115200;
    private static final int DATA_BITS = 8;
    private static final int STOP_BITS = 1;
    private static final int PARITY    = 0;
    private static final String SERIAL_PORT = "COM4";

    public static void send(String dados) throws SerialPortException, InterruptedException {
        SerialPort serialPort = new SerialPort(SERIAL_PORT);

        serialPort.openPort();
        serialPort.setParams(BAUD_RATE, DATA_BITS, STOP_BITS, PARITY, false, false);
        serialPort.writeBytes(dados.getBytes());
        Thread.sleep(2000);
        serialPort.closePort();
    }
}
