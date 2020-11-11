/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agf.sinalescolar.utils;

import com.agf.sinalescolar.model.Ocorrencia;
import com.agf.sinalescolar.model.Session;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author Airan
 */
public class CheckTimes extends Thread {
    private List<Ocorrencia> ocorrencias;
    private String agora;
    private String horaOcorrencia;
    private String dado = "";
    private int idEquipamento;
    private Calendar cal;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private CheckTimes() {

    }

    public CheckTimes(List<Ocorrencia> lista) {
        this.ocorrencias = lista;
    }

    @Override
    public void run() {
         try {
            while (true) {
                cal = Calendar.getInstance();
                agora = sdf.format(cal.getTime()); // Hora do sistema formatada
                
                for (int i = 0; i < ocorrencias.size(); i++) {
                    horaOcorrencia = ocorrencias.get(i).getHora_toque();
                    
                    if (agora.compareTo(horaOcorrencia) == 0) { // Verifica se os horários são iguais
                        idEquipamento = ocorrencias.get(i).getIdequipamento();
                        
                        switch (idEquipamento) {
                            case 1: // ID Equipamento 1 = Arduíno Mega (Transmissor)
                                dado = "liga1";
                                
                                try {
                                    SerialCommunication.send(dado); // Envia ao microcontrolador 1 para interpretar que DEVE tocar
                                } catch (SerialPortException sp) {
                                    Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, sp);
                                }
                                
                                break;
                            case 2: // ID Equipamento 2 = Arduíno UNO (Receptor)
                                dado = "liga2";
                                
                                try {
                                    SerialCommunication.send(dado); // Envia ao microcontrolador 2 para interpretar que DEVE tocar
                                } catch (SerialPortException sp) {
                                    Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, sp);
                                }
                                
                                break;
                            default: // Caso o horário exista mas não seja de algum equipamento válido
                                dado = "desliga";
                                
                                try {
                                    SerialCommunication.send(dado); // Envia ao microcontrolador para interpretar que NÃO DEVE tocar
                                } catch (SerialPortException sp) {
                                    Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, sp);
                                }
                                
                                break;
                        }
                        
                        break; // Quebra o laço de repetição da lista pois já achou o elemento com horário igual.
                    }  
                }
                
                if (dado.isEmpty()) {
                    dado = "desliga";

                    try {
                        SerialCommunication.send(dado); // Envia ao microcontrolador para interpretar que NÃO DEVE tocar
                    } catch (SerialPortException sp) {
                        Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, sp);
                    }
                    
                    break;
                }
                
                dado = "";         
                ocorrencias = Session.getInstance().getListaOcorrencias();
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
