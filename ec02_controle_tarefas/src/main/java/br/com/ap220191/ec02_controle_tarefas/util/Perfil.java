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
    
    protected String tipoPerfil;
    protected String nomeUsuario;
    protected String cargo;
    protected String setorTrabalho;

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
