/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ap220191.ec02_controle_tarefas.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego dos Santos
 */
public class Projeto {
    
    private String projNome;
    private String projFase = "";
    
    //private boolean criado=false;
    private boolean iniciado=false;
    private boolean concluido = false;
    private boolean andamento=false;
    
    private LocalDate projInicio;
    private LocalDate projConclusao;
    private LocalDate projConclusaoPrevista;
    
    private LocalDate hoje = LocalDate.now();
    
    private long mes,dias;
    
    private int tempoDias,tempoHoras;
    
    public Projeto()
    {
        
    }
    
    public void criarProjeto(String nome){
        
        if(iniciado==false&&concluido==false&&andamento==false)
        {
            projNome = nome;
            
            projFase = "Criado";
        }   
    }
    
    public void iniciarProjeto(LocalDate start,int prazoDias)
    {
        if(!iniciado&&!concluido&&!andamento)
        {
            projInicio = start;
            
            
            if(projInicio.isBefore(hoje))
            {
                //Informar erro
                JOptionPane.showMessageDialog(null, "Esta data antecede o dia de hoje! Entre com outra!");

            } else {
                if((prazoDias%30)==0){
                mes = prazoDias/30;

                projConclusaoPrevista=start.plusMonths(mes);
                
                iniciado = true;
                projFase = "Iniciado";
                
                } else if ((prazoDias/30)<1)
                {
                projConclusaoPrevista=start.plusDays(prazoDias);
                
                iniciado = true;
                projFase = "Iniciado";
                
                } else {
                mes = (prazoDias - prazoDias%30)/30;
                dias=prazoDias%30;

                projConclusaoPrevista=start.plusDays(dias);
                projConclusaoPrevista=projConclusaoPrevista.plusMonths(mes); 
                
                iniciado = true;
                projFase = "Iniciado";
                }
            }
        } else if(iniciado)
        {
            JOptionPane.showMessageDialog(null, "Este projeto já foi iniciado!");
        } else  if(concluido){  
                //Informar erro
                JOptionPane.showMessageDialog(null, "Projeto já foi concluído");
        } else if(andamento)
        {
            JOptionPane.showMessageDialog(null, "Projeto em andamento");
        }
    }
    
    public void concluirProjeto(LocalDate dataCONCLUSAO, boolean concluir){
        //Rever o atributo de entrada CONCLUIDA
        if (iniciado&&concluir&&andamento&&dataCONCLUSAO.isAfter(projInicio)){            
        //Adiconar uma condicão para CONCLUIDA
        
        projConclusao=dataCONCLUSAO;
              
        //Adicionar o tempo gasto para conclusão
        tempoDias = Period.between(projInicio,projConclusao).getDays()+30*30*Period.between(projInicio,projConclusao).getMonths()+360*Period.between(projConclusaoPrevista,projConclusao).getYears();
        tempoHoras = tempoDias*8;
        
        concluido = true;
        andamento=false;
        
        projFase="Finalizado";
        
        if(projConclusao.isAfter(projConclusaoPrevista))
        {
            int tempo = Period.between(projConclusaoPrevista,projConclusao).getDays()+30*Period.between(projConclusaoPrevista,projConclusao).getMonths()+360*Period.between(projConclusaoPrevista,projConclusao).getYears();
            JOptionPane.showMessageDialog(null, "Projeto concluído fora do prazo!\n\nData prevista: "+getProjConclusaoPrevista()+"\nData de conclusão: "+getProjConclusao()+"\n\n"+tempo+" dias a mais que o previsto");
                    
        } else if(projConclusao.isAfter(projInicio)&&projConclusao.isBefore(projConclusaoPrevista)||projConclusaoPrevista.isEqual(projConclusaoPrevista))
        {
            JOptionPane.showMessageDialog(null, "Projeto concluído dentro do prazo!\n\nData prevista: "+getProjConclusaoPrevista()+"\nData de conclusão: "+getProjConclusao()+"\n\n"+getTempoDias()+" dias e "+getTempoHoras()+" horas!");
        } else 
        {
            JOptionPane.showMessageDialog(null, "Data inválida");
        }
        
        } else {
            concluido = false;
        }
    }

    public void setProjFase(String projFase) {
        this.projFase = projFase;
    }
    
    public String getProjNome() {
        return projNome;
    }

    public int getTempoDias() {
        return tempoDias;
    }

    public int getTempoHoras() {
        return tempoHoras;
    }

    public String getProjFase() {
        return projFase;
    }

    public LocalDate getProjConclusao() {
        return projConclusao;
    }

    public LocalDate getProjConclusaoPrevista() {
        return projConclusaoPrevista;
    }
    
}
