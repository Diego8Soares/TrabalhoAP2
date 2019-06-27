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
public class Tarefa {
    
    private boolean cadastrada=false;
    private boolean iniciada=false;
    private boolean concluida=false;
    private boolean descartada=false;
    private boolean emAndamento=false;
    private boolean prioridade=false;
    
    private String tarefaNome;
/*Defeito - prioridade 1 (TRUE)
 Melhoria - Prioridade 2 (FALSE) */
    private String tarefaTipo; 
    private String tarefaRelator;
    private String tarefaExecutor;
    
    private LocalDate   tarefaInicio;
    private LocalDate   tarefaConclusaoPrevista;
    private LocalDate   tarefaConclusao;
    
    private int tempoDias,tempoHoras;
    
    private String tarefaDescricao;
    private String tarefaSolucao;
    
    private long mes,dias;
    
    private LocalDate hoje = LocalDate.now();
    
    private Perfil perfil;
    
    
    //Métodos relacionados às tarefas
    public void cadastrarTarefa(String tipoPerfil, String relator, String tarefaNome, String executor,String tipoTarefa){
        //Necessário inserir um try-catch para o caso de não serem informados nomes válidos
        
        if("Administrador".equals(tipoPerfil)&&cadastrada==false&&descartada==false&&concluida==false&&iniciada==false&&emAndamento==false){
            this.tarefaNome=tarefaNome;
                
            tarefaRelator=relator;
            tarefaExecutor=executor;
            
            tarefaTipo = tipoTarefa;
            
            if("Defeito".equals(tarefaTipo)){
                prioridade = true;
            } else if("Melhoria".equals(tarefaTipo)) {
                prioridade=false;
            } else {
                //Informar erro
                JOptionPane.showMessageDialog(null, "Este não é um tipo de tarefa válido!");
            }

            cadastrada=true;
        } else {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado!");
        }  
    }
    
    public void descreverTarefa(String descricao){
        tarefaDescricao=descricao;
    }
    
    public void iniciarTarefa(LocalDate inicio, int prazoDias){
        
        if(cadastrada&&descartada==false&&concluida==false&&iniciada==false&&emAndamento==false){
            
            tarefaInicio=inicio;
        
            if(tarefaInicio.isBefore(hoje)){
                //Informar erro
                JOptionPane.showMessageDialog(null, "Esta data antecede o dia de hoje! Entre com outra!");

            } else {
                if((prazoDias%30)==0){
                mes = prazoDias/30;

                tarefaConclusaoPrevista=inicio.plusMonths(mes);
                } else if ((prazoDias/30)<1) {

                tarefaConclusaoPrevista=inicio.plusDays(prazoDias);
                } else {
                mes = (prazoDias - prazoDias%30)/30;
                dias=prazoDias%30;

                tarefaConclusaoPrevista=inicio.plusDays(dias);
                tarefaConclusaoPrevista=tarefaConclusaoPrevista.plusMonths(mes);            
            }

            iniciada = true;
            }
        } else {
            //Informar erro
                JOptionPane.showMessageDialog(null, "Esta tarefa não possui cadastro!");
        }
        
        if(iniciada){
            emAndamento=true;
        }
             
    }
    
    public void concluirTarefa(String solucao, boolean concluida){
        //Rever o atributo de entrada CONCLUIDA
        if (cadastrada==true&&iniciada==true&&descartada==false&&concluida==true&&emAndamento==true){            
        //Adiconar uma condicão para CONCLUIDA
        //tarefaConclusao= hoje.minus();
        tarefaSolucao=solucao;
        tarefaConclusao=LocalDate.now();
              
        //Adicionar o tempo gasto para conclusão
        tempoDias = Period.between(tarefaInicio,tarefaConclusao).getDays()+30*30*Period.between(tarefaInicio,tarefaConclusao).getMonths();
        tempoHoras = tempoDias*8;
        
        this.concluida = true;
        emAndamento=false;
        
        } else {
            this.concluida = false;
        }
    }
    
    public boolean descartarTarefa(boolean descarta){
        //Adicionar condição para DESCARTADA
        if(cadastrada&&iniciada&&concluida==false&&descarta&&emAndamento){
            emAndamento=false;
            return descartada = true;
        } else {
            return descartada = false;
        }
        //Criar rotina que exclua a tarefa por nome do relator, da tarefa e qqr outro meio
        //||--> Exclusão vai ser realizada em PROJETO
    }
    
    public boolean tarefaEstado(){
        if(cadastrada&&iniciada&&concluida==false&&descartada==false&&emAndamento){
           return emAndamento=true;          
        } else {
            return emAndamento=false;
        }       
    }
    
    
    
}
