/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ap220191.ec02_controle_tarefas.util;

/**
 *
 * @author Diego dos Santos
 */
public class Perfil {
    
    private String tipoPerfil;
    private String nomeUsuario;
    private String cargo;
    private String setorTrabalho;
    
    public void Perfil(){
        
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
