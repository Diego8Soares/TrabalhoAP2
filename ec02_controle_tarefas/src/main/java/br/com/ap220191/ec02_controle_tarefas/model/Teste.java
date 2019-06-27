/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package br.com.ap220191.ec01_faculdade.model;
package br.com.ap220191.ec02_controle_tarefas.model;

import br.com.ap220191.ec02_controle_tarefas.util.Perfil;
import br.com.ap220191.ec02_controle_tarefas.util.Tarefa;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author gilmario
 */
public class Teste {
    public static void main(String args[]){
        
        LocalDate hoje = LocalDate.now();        
        LocalDate mesQueVem = hoje.plusDays(3);
        
        mesQueVem = mesQueVem.plusMonths(4);
        int dias = Period.between(hoje,mesQueVem).getDays() + 30*Period.between(hoje,mesQueVem).getMonths();
        
        Tarefa teste = new Tarefa();
        
        Perfil perfil = new Perfil();
        
        perfil.Perfil("Diego", "Produtor", "Administrador", "Oficial");
        
        teste.cadastrarTarefa(perfil.getTipoPerfil(), "Saulo", "Caminhar", "Dupla", "Melhoria");
        teste.iniciarTarefa(hoje, dias);
        
        boolean b=true;
        
        if(!b){
            System.out.println("Tarefa descartada");
        } else {
            System.out.println("Tarefa em andamento!");
        }        
        
    }
    
}
