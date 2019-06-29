/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package br.com.ap220191.ec01_faculdade.model;
package br.com.ap220191.ec02_controle_tarefas.model;

import br.com.ap220191.ec02_controle_tarefas.util.Perfil;
import br.com.ap220191.ec02_controle_tarefas.util.Projeto;
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
        
        Tarefa tf = new Tarefa();
        
        Perfil p1 = new Perfil();
        Perfil p2 = new Perfil();
        
        Projeto prj = new Projeto();
        
        p1.Perfil("Diego", "Produtor", "Administrador", "Oficial");
        p2.Perfil("José", "Músico", "Usuário", "Oficial");
        
        p1.adicionarTarefa(tf);
        p2.adicionarTarefa(tf);
        p1.adicionarProjeto(prj);
        p2.adicionarProjeto(prj);
          
        tf.cadastrarTarefa(p1, p2, "Dupla", "Melhoria");
        tf.iniciarTarefa(hoje, dias);
        
        tf.adicionarPerfil(p1);
        tf.adicionarPerfil(p2);
        tf.setProjetoVinculadaNome(prj);
        
        prj.adicionarTarefa(tf);
        prj.criarProjeto("Banda", true);
        
        prj.adicionarPerfil(p1);
        prj.adicionarPerfil(p2);
        
        prj.adicionarTarefa(tf);
               
        //boolean b=false;
        
        if(tf.tarefaEstado()){
            System.out.println("Tarefa em andamento!\n");
        } else {
            System.out.println("Tarefa descartada!\n");
        }
        
        for(int i=0; i<tf.quantidadePerfis();i++){
            System.out.println(tf.getPerfisTarefa(i).getTipoPerfil());
        }
        
        for(int i=0; i<p1.quantidadeTarefas();i++){
            System.out.println("\n"+p1.getTarefasPerfil(i).getTarefaNome()+"\n"+p1.getTarefasPerfil(i).getTarefaTipo());
        }
        System.out.println("");
        
        for(int i=0; i<prj.quantidadePerfis();i++){
            System.out.println(prj.getPerfil(i).getTipoPerfil());
        }
    }
    
}
