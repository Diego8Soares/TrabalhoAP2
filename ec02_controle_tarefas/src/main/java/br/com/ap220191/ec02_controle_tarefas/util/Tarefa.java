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
public class Tarefa {
    
    private boolean cadastrada= false;
    private boolean iniciada  = false;
    private boolean concluida = false;
    private boolean descartada= false;
    private boolean emAndamento=false;
    private boolean prioridade= false;
    
    private String tarefaNome;
/*Defeito - prioridade 1 (TRUE)
 Melhoria - Prioridade 2 (FALSE) */
    private String tarefaTipo; 
    
    private LocalDate   tarefaInicio;
    private LocalDate   tarefaConclusaoPrevista;
    private LocalDate   tarefaConclusao;
    
    private int tempoDias,tempoHoras;
    
    private String tarefaDescricao;
    private String tarefaSolucao;
    
    private long mes,dias;
    
    private LocalDate hoje = LocalDate.now();
    
    private Perfil tarefaRelator;
    private Perfil tarefaExecutor;
    
    private ArrayList<Perfil> perfisTarefa;
    
    private Projeto projetoVinculadaNome;
        
    
    
    public Tarefa(){
        perfisTarefa = new ArrayList<Perfil>();
    }
    
    
    
/*Primeiramente Adicionar os perfis no objeto e em seguida extra�-los e p�-los 
no m�todo cadastrar*/
    public void adicionarPerfil(Perfil perfil){
        perfisTarefa.add(perfil);
    }
    public int quantidadePerfis(){
        return perfisTarefa.size();
    }
    public void getPerfil(int posicao){
        perfisTarefa.get(posicao);
    }
    public Perfil getPerfisTarefa(int posicao) {
        return perfisTarefa.get(posicao);
    }
    
    
    
    public void setProjetoVinculadaNome(Projeto projetoVinculada) {
        this.projetoVinculadaNome = projetoVinculada;
    }
    public Projeto getProjetoVinculadaNome() {
        return projetoVinculadaNome;
    }


    /* public ArrayList<Perfil> getPerfisTarefa() {
        return perfisTarefa;
    }*/
    
    
    
    //M�todos relacionados �s tarefas
    public void cadastrarTarefa(Perfil relator, Perfil executor, String tarefaNome, String tipoTarefa){
        //Necess�rio inserir um try-catch para o caso de n�o serem informados nomes v�lidos
        
        if("Administrador".equals(relator.getTipoPerfil())&&cadastrada==false&&descartada==false&&concluida==false&&iniciada==false&&emAndamento==false){
            this.tarefaNome=tarefaNome;
        //Criar m�todo que chama o objeto PERFIL e ent�o obt�m dele os dados   
            tarefaRelator=relator;
            tarefaExecutor=executor;
            
            tarefaTipo = tipoTarefa;
            
            if("DEFEITO".equals(tarefaTipo)){
                prioridade = true;
            } else if("MELHORIA".equals(tarefaTipo)) {
                prioridade=false;
            } else {
                //Informar erro
                JOptionPane.showMessageDialog(null, "Este n�o � um tipo de tarefa v�lido!");
            }
            
            cadastrada=true;
        } else {
            JOptionPane.showMessageDialog(null, "Perfil n�o autorizado!");
        }  
    }
        
    public void descreverTarefa(String descricao){
        tarefaDescricao=descricao;
    }
    
    public void iniciarTarefa(LocalDate inicio, int prazoDias){
        
        if("Usu�rio".equals(tarefaExecutor.getTipoPerfil())&&cadastrada&&descartada==false&&concluida==false&&iniciada==false&&emAndamento==false){
            
            tarefaInicio=inicio;
        
            if(tarefaInicio.isBefore(hoje)){
                //Informar erro
                JOptionPane.showMessageDialog(null, "Esta data antecede o dia de hoje! Entre com outra!");

            } else if((prazoDias%30)==0){ 
                
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
            emAndamento=true;
        } else {
            //Informar erro
            JOptionPane.showMessageDialog(null, "Esta tarefa j� foi iniciada!");
            }
        }
        
    
    
    public void concluirTarefa(String solucao, boolean concluida){
        /*Rever o atributo de entrada CONCLUIDA*/
        if ("Usu�rio".equals(tarefaExecutor.getTipoPerfil())&&cadastrada==true&&iniciada==true&&descartada==false&&concluida==true&&emAndamento==true){            
        //Adiconar uma condic�o para CONCLUIDA
        //tarefaConclusao= hoje.minus();
        tarefaSolucao=solucao;
        tarefaConclusao=LocalDate.now();
              
        //Adicionar o tempo gasto para conclus�o
        tempoDias = Period.between(tarefaInicio,tarefaConclusao).getDays()+30*30*Period.between(tarefaInicio,tarefaConclusao).getMonths();
        tempoHoras = tempoDias*8;
        
        this.concluida = true;
        emAndamento=false;
        
        } else {
            this.concluida = false;
        }
    }
    
    public boolean descartarTarefa(boolean descarta){
        //Adicionar condi��o para DESCARTADA
        if("Usu�rio".equals(tarefaExecutor.getTipoPerfil())&&cadastrada&&iniciada&&concluida==false&&descarta&&emAndamento){
            emAndamento=false;
            return descartada = true;
        } else {
            return descartada = false;
        }
        //Criar rotina que exclua a tarefa por nome do relator, da tarefa e qqr outro meio
        //||--> Exclus�o vai ser realizada em PROJETO
    }
    
    public boolean tarefaEstado(){
        if(cadastrada&&iniciada&&concluida==false&&descartada==false){
           return emAndamento=true;          
        } else {
            return emAndamento=false;
        }       
    }

    public String getTarefaNome() {
        return tarefaNome;
    }
    
    //�til para coletar informa��es do funcion�rio
    public Perfil getTarefaRelator() {
        return tarefaRelator;
    }

    public Perfil getTarefaExecutor() {
        return tarefaExecutor;
    }

    public String getTarefaTipo() {
        return tarefaTipo;
    }

    public String getTarefaDescricao() {
        return tarefaDescricao;
    }

    public String getTarefaSolucao() {
        return tarefaSolucao;
    }
    
    @Override
    public String toString(){
        return ""+tarefaEstado();
    }
    
}
