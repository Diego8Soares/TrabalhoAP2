/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ap220191.ec02_controle_tarefas.util;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego dos Santos
 */
public class Consulta {
//Classe apenas irá puxar os dados do projeto e apresentar
    /*Aparemente é melhor chamar um projeto inteiro e então gerar uma rotina
    pra verificar cada uma das verficações abaixo*/
    private ArrayList<Tarefa> tarefasANALISE = new ArrayList<>();
    
    public void porPROJETO(Perfil administrador, String projetoNome, ArrayList<Tarefa> tarefasLista)
    {
        //Verifica se o perfil é administrador
        if(administrador.getTipoPerfil()=="Administrador")
        {
            if("SELECIONE".equals(projetoNome))
            {
                
            }
            else
            {
                String lista = "";

                for(int i=0; i<tarefasLista.size();i++)
                {
                    if(tarefasLista.get(i).getProjetoVinculado().getProjNome()==projetoNome)
                    {
                        lista+= " *"+tarefasLista.get(i).getTarefaNome()+"*";
                    }
                }

                JOptionPane.showMessageDialog(null, "O projeto "+projetoNome.toUpperCase()+" possui a(s) tarefa(s) "+lista.toUpperCase()+" vinculada(s) a ele.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado");
        }
    }
    
    public void porFASE(Perfil administrador, String faseProjeto, ArrayList<Tarefa> tarefasLista)
    {
        if(administrador.getTipoPerfil()=="Administrador")
        {
            if("SELECIONE".equals(faseProjeto))
            {

            } else {
                for (int i=0; i<tarefasLista.size();i++)
                {
                    if(tarefasLista.get(i).getProjetoVinculado().getProjFase()==faseProjeto)
                    {
                        JOptionPane.showMessageDialog(null,"A tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" pertence a "+faseProjeto.toUpperCase()+" do projeto "+tarefasLista.get(i).getProjetoVinculado().getProjNome().toUpperCase());
                    }
                }
                
                
            } 
        } else
        {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado");
        }
    }   
            
    public void porTipoDeTAREFA(Perfil administrador, String tipoTarefa, ArrayList<Tarefa> tarefasLista)
    {
        if(administrador.getTipoPerfil()=="Administrador")
        {
            if("SELECIONE".equals(tipoTarefa))
            {

            } else
            {
                String str = "";

                for (int i=0; i<tarefasLista.size();i++)
                {
                    if(tarefasLista.get(i).getTarefaTipo()==tipoTarefa)
                    {
                        str+=" *"+tarefasLista.get(i).getTarefaNome().toUpperCase()+"* ";
                    }
                }

                JOptionPane.showMessageDialog(null,"A(s) tarefa(s)"+str+" é(são) do tipo "+tipoTarefa.toUpperCase());
            }
        } else 
        {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado");
        }
    }
        
    public void porEstadoTAREFA(Perfil administrador, String estadoTarefa, ArrayList<Tarefa> tarefasLista)
    {
        if(administrador.getTipoPerfil()=="Administrador")
        {
            if("SELECIONE".equals(estadoTarefa))
            {

            } else {
                String lista="";

                for(int i=0; i<tarefasLista.size();i++)
                {
                    if(estadoTarefa==tarefasLista.get(i).getEstado())
                    {
                        lista+=" *"+tarefasLista.get(i).getTarefaNome()+"*\n";
                    }
                }
                
                JOptionPane.showMessageDialog(null, estadoTarefa.toUpperCase()+":\n"+lista);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado");
        }
    }
    
    public void porRELATOR(Perfil administrador, String relator, ArrayList<Tarefa> tarefasLista)
    {
        if(administrador.getTipoPerfil()=="Administrador")
        {
            if("SELECIONE".equals(relator))
            {

            } 
            else 
            {
                for (int i=0; i<tarefasLista.size();i++)
                {
                    if(tarefasLista.get(i).getTarefaRelator().getNomeUsuario()==relator)
                    {
                        JOptionPane.showMessageDialog(null, "O relator "+relator.toUpperCase()+" foi criador da tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" que tem por objetivo "+tarefasLista.get(i).getTarefaDescricao().toUpperCase());
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null,"Este não é o relator desta tarefa");
                    }
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado");
        }
    }
    
    public void porEXECUTOR(Perfil administrador, String executor, ArrayList<Tarefa> tarefasLista)
    {
        if(administrador.getTipoPerfil()=="Administrador")
        {
            if("SELECIONE".equals(executor))
            {

            } 
            else 
            {
                for (int i=0; i<tarefasLista.size();i++)
                {
                    if(tarefasLista.get(i).getTarefaExecutor().getNomeUsuario()==executor)
                    {
                        JOptionPane.showMessageDialog(null, "O executor "+executor.toUpperCase()+" é responsável pela tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" que tem por objetivo "+tarefasLista.get(i).getTarefaDescricao().toUpperCase());
                    }
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Perfil não autorizado");
        }
    }
}
