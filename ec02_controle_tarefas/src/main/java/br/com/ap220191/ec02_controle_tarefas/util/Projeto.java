/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ap220191.ec02_controle_tarefas.util;

import java.time.LocalDate;
import java.time.Period;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego dos Santos
 */
public class Projeto extends Tarefa{
    
    protected String projNome;
    protected String projFase = "";
    
    //protected boolean criado=false;
    protected boolean iniciado=false;
    protected boolean concluido = false;
    protected boolean projeto=false; //Define se será um projeto ou tarefa simples
    protected boolean andamento=false;
    
    protected LocalDate projInicio;
    protected LocalDate projConclusao;
    protected LocalDate projConclusaoPrevista;
    
    private long mes,dias;
            
    private int tempoDias,tempoHoras;
    
    //protected LocalDate hoje = LocalDate.now();
    
    public void criarProjeto(String nome, boolean projetoOUtarefa ){
        
        if(iniciado==false&&concluido==false&&andamento==false&&projetoOUtarefa){
            projNome = nome;
            
            projFase = "Criado!";
        }
        
        if(iniciado==false&&concluido==false&&andamento==false&&projetoOUtarefa==false){
            projNome = "Tarefa simples";
            
            projFase = "Criado!";
        }
        
        
        
        this.projeto = projetoOUtarefa;
        //Se FALSE: impedir de gerar tarefas (Sistema)   
    }
    
    public void iniciarProjeto(LocalDate start,int prazoDias){
    //Para os casos de ser uma TAREFA SIMPLES, chama o método de iniciar tarefa
        if(iniciado==false&&concluido==false&&andamento==false&&projeto){
            projInicio = start;
            
            iniciado = true;
            
            if(projInicio.isBefore(hoje)){
                //Informar erro
                JOptionPane.showMessageDialog(null, "Esta data antecede o dia de hoje! Entre com outra!");

            } else {
                if((prazoDias%30)==0){
                mes = prazoDias/30;

                projConclusaoPrevista=start.plusMonths(mes);
                } else if ((prazoDias/30)<1) {

                projConclusaoPrevista=start.plusDays(prazoDias);
                } else {
                mes = (prazoDias - prazoDias%30)/30;
                dias=prazoDias%30;

                projConclusaoPrevista=start.plusDays(dias);
                projConclusaoPrevista=projConclusaoPrevista.plusMonths(mes);            
            }

            iniciado = true;
            
            projFase = "Início!";
            }
        } else {
            //Informar erro
                JOptionPane.showMessageDialog(null, "Este projeto não possui cadastro!");
        }
        
        if(iniciado){
            andamento=true;
        }
    }
    
    public void concluirProjeto(boolean concluir){
        //Rever o atributo de entrada CONCLUIDA
        if (iniciado&&concluir&&andamento&&projeto){            
        //Adiconar uma condicão para CONCLUIDA
        
        projConclusao=LocalDate.now();
              
        //Adicionar o tempo gasto para conclusão
        tempoDias = Period.between(projInicio,projConclusao).getDays()+30*30*Period.between(tarefaInicio,tarefaConclusao).getMonths();
        tempoHoras = tempoDias*8;
        
        concluido = true;
        andamento=false;
        
        } else {
            concluido = false;
        }
    }
    
    public void faseProjeto(String fase){
        
        //Possível tratamento de excessão
        if(iniciado&&concluido==false&&andamento&&projeto){
            projFase = fase;
        }   
    }
}
