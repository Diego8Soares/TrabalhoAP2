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
                    try
                    {
                        if(tarefasLista.get(i).getProjetoVinculado().getProjNome()==projetoNome)
                        {
                            lista+= "\n*"+tarefasLista.get(i).getTarefaNome()+"*";
                        }
                    } catch (Exception e)
                    {
                        
                    }
                }

                JOptionPane.showMessageDialog(null, "O projeto "+projetoNome.toUpperCase()+" possui a(s) tarefa(s): "+lista.toUpperCase());
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
                
                String tarefas = "";
                String projetos = "";
                
                for (int i=0; i<tarefasLista.size();i++)
                {
                    //JOptionPane.showMessageDialog(null,tarefasLista.get(i).getProjetoVinculado());
                    try 
                    {
                        if(tarefasLista.get(i).getProjetoVinculado().getProjFase()==faseProjeto)
                        {
                            tarefas+="\n*"+tarefasLista.get(i).getTarefaNome().toUpperCase()+"          -->        "+tarefasLista.get(i).getProjetoVinculado().getProjNome()+"";
                        }
                        
                        if(i==(tarefasLista.size()-1))
                        {
                            JOptionPane.showMessageDialog(null,"Tarefas pertencentes à "+faseProjeto.toUpperCase()+": \nTarefa        -->         Projeto "+tarefas+""+projetos);
                        }
                        
                    } catch (Exception e) 
                    {
                        JOptionPane.showMessageDialog(null,"Tarefa"+tarefasLista.get(i).getTarefaNome().toUpperCase()+" sem vínculo");
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
                        str+="\n*"+tarefasLista.get(i).getTarefaNome().toUpperCase()+"*";
                    }
                }

                JOptionPane.showMessageDialog(null,"Tarefa(s) do tipo "+tipoTarefa.toUpperCase()+": "+str);
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
                    try 
                    {
                        if(estadoTarefa==tarefasLista.get(i).getEstado())
                        {
                            lista+="\n*"+tarefasLista.get(i).getTarefaNome()+"*";
                        }
                    } catch (Exception e) 
                    {
                        
                    }
                }
                
                JOptionPane.showMessageDialog(null, estadoTarefa.toUpperCase()+":"+lista);
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
                    try
                    {
                        if(tarefasLista.get(i).getTarefaRelator().getNomeUsuario()==relator)
                        {
                            JOptionPane.showMessageDialog(null, "O relator "+relator.toUpperCase()+" foi criador da tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+"!\nTendo esta o objetivo "+tarefasLista.get(i).getTarefaDescricao().toUpperCase()+"\n"+tarefasLista.get(i).getTarefaDescricao().toUpperCase()+" pertencente ao projeto "+tarefasLista.get(i).getProjetoVinculado().getProjNome());
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null,"Este não é o relator desta tarefa");
                        }
                        
                    } catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Executor:"+relator+"\nObjeto: "+tarefasLista.get(i).getTarefaRelator()+"\nNome do obj: "+tarefasLista.get(i).getTarefaRelator().getNomeUsuario()+"\nNome: "+tarefasLista.get(i).getTarefaNome()+"\n"+tarefasLista.get(i).getTarefaDescricao()+"\n"+tarefasLista.get(i).getProjetoVinculado());
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
                    try
                    {
                        if(tarefasLista.get(i).getTarefaExecutor().getNomeUsuario()==executor)
                        {
                            JOptionPane.showMessageDialog(null, "O executor "+executor.toUpperCase()+" é responsável pela tarefa "+tarefasLista.get(i).getTarefaNome().toUpperCase()+" que tem por objetivo "+tarefasLista.get(i).getTarefaDescricao().toUpperCase());
                        }
                    } catch (Exception e)
                    {
                        
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
