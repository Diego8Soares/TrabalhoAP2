/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ap220191.ec02_controle_tarefas.util;

import java.util.ArrayList;

/**
 *
 * @author Diego dos Santos
 */
public class Perfil {
    
    private String tipoPerfil;
    private String nomeUsuario;
    private String cargo;
    private String setorTrabalho;
    
    private ArrayList<Tarefa> tarefasPerfil; // 14/07 - Excluir
    private ArrayList<Projeto> projetosPerfil; // Excluir
    
    public Perfil(){
        tarefasPerfil  = new ArrayList<Tarefa>(); // Excluir
        projetosPerfil = new ArrayList<Projeto>();  // Excluir
    }
    
    
    public void adicionarTarefa(Tarefa tarefa)  // Excluir
    {
        tarefasPerfil.add(tarefa);
    }
    public int quantidadeTarefas()
    {
        return tarefasPerfil.size();
    }
    public Tarefa getTarefasPerfil(int posicao)
    {
        return tarefasPerfil.get(posicao);
    }
    
    public void atualizaTarefa(int posicao, Tarefa tarefa)
    {
        tarefasPerfil.set(posicao,tarefa);
    }
    
    public void atualizaProjeto(int posicao, Projeto projeto)
    {
        projetosPerfil.set(posicao,projeto);
    }
    
    
    
    public void adicionarProjeto(Projeto projeto){
        projetosPerfil.add(projeto);
    }
    public int quantidadeProjetos(){
        return projetosPerfil.size();
    }public Projeto getProjetosPerfil(int posicao) {
        return projetosPerfil.get(posicao); // Excluir
    }
      
  
    public void Perfil(String nome, String Cargo, String perfil, String setor){
        nomeUsuario=nome;
        cargo=Cargo;
        setorTrabalho=setor;
        tipoPerfil = perfil;    
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSetorTrabalho() {
        return setorTrabalho;
    }

    public void setSetorTrabalho(String setorTrabalho) {
        this.setorTrabalho = setorTrabalho;
    }
    
}
