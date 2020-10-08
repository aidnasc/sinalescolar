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
    private static final int PARITY    = 0;
    private static final String SERIAL_PORT = "COM4";

    /* Método para pegar a hora atual em hh:mm:ss, comparar com cada horário de uma lista e decidir o que enviar
    LocalTime.now().truncatedTo(ChronoUnit.SECONDS); */
    
    /* Verificar como fazer, executar uma Thread para ficar pegando a hora do sistema
    e ver se bate com alguma hora na lista de ocorrências. Quando bater, enviar
    mensagem para tocar, senão Thread.sleep e reinicia de tanto em tanto tempo. */
    public static void enviar(String dados) {
        SerialPort serialPort = new SerialPort(SERIAL_PORT);

        try {     	
            System.out.println("Porta aberta: " + serialPort.openPort());
            System.out.println("Parâmetros configurados: " + serialPort.setParams(BAUD_RATE, DATA_BITS, STOP_BITS, PARITY));
            System.out.println("Mensagem enviada: " + serialPort.writeBytes(dados.getBytes()));
            System.out.println("Porta fechada: " + serialPort.closePort());
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        }
    }
}
