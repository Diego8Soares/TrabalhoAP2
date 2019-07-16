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
    private boolean projeto=false; //Define se será um projeto ou tarefa simples
    private boolean andamento=false;
    
    private LocalDate projInicio;
    private LocalDate projConclusao;
    private LocalDate projConclusaoPrevista;
    
    private LocalDate hoje = LocalDate.now();
    
    private ArrayList<Tarefa> tarefasProjeto;
    private ArrayList<Perfil> perfisProjeto;
    
    private long mes,dias;
    
    private int tempoDias,tempoHoras;
    
    //private LocalDate hoje = LocalDate.now();
    public Projeto(){
        //tarefasProjeto = new ArrayList<Tarefa>();
       // perfisProjeto  = new ArrayList<Perfil>();
    }
    
    public void adicionarTarefa(Tarefa tarefa){
        tarefasProjeto.add(tarefa);
    }
    public int quantidadeTarefas(){
        return tarefasProjeto.size();
    }
    public Tarefa getTarefas(int posicao){
        return tarefasProjeto.get(posicao);
    }
    
    
    public void adicionarPerfil(Perfil perfil){
        perfisProjeto.add(perfil);
    } 
    public int quantidadePerfis(){
        return perfisProjeto.size();
    }
    public Perfil getPerfil(int posicao){
        return perfisProjeto.get(posicao);
    }
    
    
    
    public void criarProjeto(String nome){
        
        if(iniciado==false&&concluido==false&&andamento==false)
        {
            projNome = nome;
            
            projFase = "Criado!";
        }   
    }
    
    public void iniciarProjeto(LocalDate start,int prazoDias)
    {
        if(iniciado==false&&concluido==false&&andamento==false&&projeto)
        {
            projInicio = start;
            
            iniciado = true;
            
            if(projInicio.isBefore(hoje))
            {
                //Informar erro
                JOptionPane.showMessageDialog(null, "Esta data antecede o dia de hoje! Entre com outra!");

            } else {
                if((prazoDias%30)==0){
                mes = prazoDias/30;

                projConclusaoPrevista=start.plusMonths(mes);
                } else if ((prazoDias/30)<1)
                {
                projConclusaoPrevista=start.plusDays(prazoDias);
                } else {
                mes = (prazoDias - prazoDias%30)/30;
                dias=prazoDias%30;

                projConclusaoPrevista=start.plusDays(dias);
                projConclusaoPrevista=projConclusaoPrevista.plusMonths(mes);            
            }

            iniciado = true;
            
            projFase = "Iniciado";
            }
        } else if(iniciado)
        {
            JOptionPane.showMessageDialog(null, "Este projeto já foi iniciado!");
        } else  if(!iniciado){  
                //Informar erro
                JOptionPane.showMessageDialog(null, "Este projeto não possui cadastro!");
        }
        
        if(iniciado){
            andamento=true;
        }
    }
    
    public void concluirProjeto(LocalDate dataCONCLUSAO, boolean concluir){
        //Rever o atributo de entrada CONCLUIDA
        if (iniciado&&concluir&&andamento){            
        //Adiconar uma condicão para CONCLUIDA
        
        projConclusao=dataCONCLUSAO;
              
        //Adicionar o tempo gasto para conclusão
        tempoDias = Period.between(projInicio,projConclusao).getDays()+30*30*Period.between(projInicio,projConclusao).getMonths();
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

    public String getProjNome() {
        return projNome;
    }

    public void setProjFase(String projFase) {
        this.projFase = projFase;
    }

    public String getProjFase() {
        return projFase;
    }
    
    
}
