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
    ArrayList<Tarefa> tarefa;
    
    public void porPROJETO(String projetoNome, ArrayList<Tarefa> tarefasLista)
    {
        tarefa=tarefasLista;
        
        if("S/A".equals(projetoNome))
        {
            
        }else
        {
            String lista = "";
            
            for(int i=0; i<tarefa.size();i++)
            {
                if(tarefa.get(i).getProjetoVinculadoNome().getProjNome()==projetoNome)
                {
                    lista+= " *"+tarefa.get(i).getTarefaNome()+"*";
                }
            }

            JOptionPane.showMessageDialog(null, "O projeto "+projetoNome.toUpperCase()+" possui a(s) tarefa(s) "+lista+" vinculada(s) a ele.");
        }
        
    }
    
    public void porFASE(String faseProjeto, ArrayList<Tarefa> tarefasLista)
    {
        if("S/A".equals(faseProjeto))
        {
            
        } else {
            for (int i=0; i<tarefasLista.size();i++)
            {
                if(tarefasLista.get(i).getProjetoVinculadoNome().getProjFase()==faseProjeto)
                {
                    JOptionPane.showMessageDialog(null,"A tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" pertence a "+faseProjeto.toUpperCase()+" do projeto "+tarefasLista.get(i).getProjetoVinculadoNome().getProjNome().toUpperCase());
                }
            }
        }
          
    }      
            
    public void porTipoDeTAREFA(String tipoTarefa, ArrayList<Tarefa> tarefasLista)
    {
        if("S/A".equals(tipoTarefa))
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
    }
        
    public void porEstadoTAREFA(String estadoTarefa, ArrayList<Tarefa> tarefasLista)
    {
        if("S/A".equals(estadoTarefa))
        {
            
        } else {
            String lista="";

            for(int i=0; i<tarefasLista.size();i++)
            {
                if(estadoTarefa==tarefasLista.get(i).getEstado())
                {
                    lista+=" *"+tarefasLista.get(i).getTarefaNome().toUpperCase()+"* ";
                }
            }
        
            JOptionPane.showMessageDialog(null, estadoTarefa.toUpperCase()+": "+lista);
        }
    }
    
    
    
    public void porRELATOR(String relator, ArrayList<Tarefa> tarefasLista)
    {
        if("S/A".equals(relator))
        {
            
        } else {
            for (int i=0; i<tarefasLista.size();i++)
            {
                if(tarefasLista.get(i).getTarefaRelator().getNomeUsuario()==relator)
                {
                    JOptionPane.showMessageDialog(null, "O relator "+relator.toUpperCase()+" foi criador da tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" que tem por objetivo "+tarefasLista.get(i).getTarefaDescricao().toUpperCase());
                }
            }
        }
    }
    
    public void porEXECUTOR(String executor, ArrayList<Tarefa> tarefasLista)
    {
        if("S/A".equals(executor))
        {
            
        } else {
            for (int i=0; i<tarefasLista.size();i++)
            {
                if(tarefasLista.get(i).getTarefaExecutor().getNomeUsuario()==executor)
                {
                    JOptionPane.showMessageDialog(null, "O executor "+executor.toUpperCase()+" é responsável pela tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" que tem por objetivo "+tarefasLista.get(i).getTarefaDescricao().toUpperCase());
                }
            }
        }
        
    }

}
