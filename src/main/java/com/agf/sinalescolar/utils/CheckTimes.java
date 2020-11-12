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
                String dado = "";
                int idEquipamento = 0;
                int numOcorrencias = 0;
                cal = Calendar.getInstance();
                agora = sdf.format(cal.getTime()); // Hora do sistema formatada
                System.out.println("Horário " + agora);
                if (agora.charAt(6) == '0' && agora.charAt(7) == '0') {
                    for (int i = 0; i < ocorrencias.size(); i++) {
                        horaOcorrencia = ocorrencias.get(i).getHora_toque();
                        if (agora.compareTo(horaOcorrencia) == 0) { // Verifica se os horários são iguais
                            if (numOcorrencias == 0) {
                                idEquipamento = ocorrencias.get(i).getIdequipamento();
                            }
                            numOcorrencias++;
                        }
                    }
                    if (numOcorrencias == 2) {
                        dado = "ligat";
                    } else if (idEquipamento == 2) {
                        dado = "liga2";
                    } else if (idEquipamento == 1) {
                        dado = "liga1";
                    }
                    if (!dado.isEmpty()) {
                        SerialCommunication.send(dado);
                        Thread.sleep(2000);
                    }
                }
                Thread.sleep(1000);
                ocorrencias = Session.getInstance().getListaOcorrencias();
            }
        } catch (Exception ex) {
            Logger.getLogger(CheckTimes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
