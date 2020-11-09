/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.utils;

import com.agf.sinalescolar.model.Session;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author Airan
 */
public class CheckTimes extends Thread {
    private List<LocalTime> horarios;
    private LocalTime agora;

    private CheckTimes() {

    }

    public CheckTimes(List<LocalTime> lista) {
        this.horarios = lista;
    }

    @Override
    public void run() {
         try {
            while (true) {
                agora = LocalTime.now(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS); // Hora do sistema formatada
                
                for (int i = 0; i < horarios.size(); i++) {
                    LocalTime horalista = horarios.get(i);
                    horalista.truncatedTo(ChronoUnit.SECONDS);
                    
                    if (agora.compareTo(horalista) == 0) { // Verifica se os horários são iguais
                        try {
                            SerialCommunication.send("1"); // Envia 1 ao microcontrolador para interpretar que deve tocar
                        } catch (SerialPortException sp) {
                            Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, sp);
                        }
                    } else {
                        try {
                            SerialCommunication.send("0"); // Envia 0 ao microcontrolador para interpretar que não deve tocar
                        } catch (SerialPortException sp) {
                            Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, sp);
                        }
                    }
                }
               
                horarios = Session.getInstance().getListaHorarios();
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
