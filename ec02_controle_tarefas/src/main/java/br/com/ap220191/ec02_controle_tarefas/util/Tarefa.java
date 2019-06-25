/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ap220191.ec02_controle_tarefas.util;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego dos Santos
 */
public class Tarefa extends Perfil{
    
    protected boolean cadastrada=false;
    protected boolean iniciada=false;
    protected boolean concluida=false;
    protected boolean descartada=false;
    protected boolean emAndamento=false; 
    
    protected String tarefaNome;
    protected String tarefaTipo;
    protected String tarefaRelator;
    protected String tarefaExecutor;
    
    protected LocalDate   tarefaInicio;
    protected LocalDate   tarefaConclusaoPrevista;
    protected LocalDate   tarefaConclusao;
    
    protected String tarefaDescricao;
    protected String tarefaSolucao;
    
    protected int tempoDias;
    private long mes,dias;
    
    protected LocalDate hoje = LocalDate.now();
    
    
    //Métodos relacionados às tarefas
    public void cadastrarTarefa(String tipoPerfil, String relator, String tarefaNome, String executor, String descricao){
        //Necessário inserir um try-catch para o caso de não serem informados nomes válidos
        
        if("Administrador".equals(tipoPerfil)&&cadastrada==false&&descartada==false&&concluida==false&&iniciada==false){
            this.tarefaNome=tarefaNome;
                
            tarefaRelator=relator;
            tarefaExecutor=executor;

            tarefaDescricao=descricao;

            cadastrada=true;
        } else {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado!");
        }
        
    }
    
    
    public void iniciarTarefa(LocalDate inicio, int prazoDias){
        
        if(cadastrada&&descartada==false&&concluida==false&&iniciada==false){
            
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
                tarefaConclusaoPrevista=inicio.plusMonths(mes);            
            }

            iniciada = true;
            }
        } else {
            //Informar erro
                JOptionPane.showMessageDialog(null, "Esta tarefa não possui cadastro!");
        }
        
        
    }
    
    public void concluirTarefa(String solucao, boolean concluida){
        //Rever o atributo de entrada CONCLUIDA
        if (cadastrada==true&&iniciada==true&&descartada==false){            
        //Adiconar uma condicão para CONCLUIDA
        //tarefaConclusao= hoje.minus();
        tarefaSolucao=solucao;
        this.concluida = true;
        
        } else {
            this.concluida = false;
        }
    }
    
    public void descartarTarefa(boolean descarta){
        //Adicionar condição para DESCARTADA
        if(cadastrada=true&&iniciada==true&&concluida==false&&descarta==true){
            descartada = true;
        }
        //Criar rotina que exclua a tarefa por nome do relator, da tarefa e qqr outro meio
        
    }
    
    public boolean tarefaEstado(){
        if(cadastrada&&iniciada&&concluida==false&&descartada==false){
           return !emAndamento;          
        } else {
            return emAndamento;
        }       
    }
    
}
