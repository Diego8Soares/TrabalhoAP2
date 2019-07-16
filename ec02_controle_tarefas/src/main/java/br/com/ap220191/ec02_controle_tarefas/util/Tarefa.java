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
    /*Defeito - prioridade 1 (TRUE)
    Melhoria - Prioridade 2 (FALSE) */
    
    private String tarefaNome;
    private String tarefaTipo;
    private String estado; 
    
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
    
    private Projeto projetoVinculado;
        
    public Tarefa()
    {
        
    }
    
    public void setProjetoVinculado(Projeto projetoVinculado) 
    {
        this.projetoVinculado = projetoVinculado;
    }
    
    public Projeto getProjetoVinculado()
    {
        return projetoVinculado;
    }
    
    //M�todos relacionados �s tarefas
    public void cadastrarTarefa(Perfil relator, String tarefaNome, String tipoTarefa)
    {
        //Necess�rio inserir um try-catch para o caso de n�o serem informados nomes v�lidos
        if("Administrador".equals(relator.getTipoPerfil())&&!cadastrada&&!descartada&&!concluida&&!iniciada&&!emAndamento)
        {
            this.tarefaNome=tarefaNome;
            
            //Criar m�todo que chama o objeto PERFIL e ent�o obt�m dele os dados   
            setTarefaRelator(relator);
            
            tarefaTipo = tipoTarefa;
            
            if("DEFEITO".equals(tarefaTipo))
            {
                prioridade = true;
                cadastrada=true;
            } else if("MELHORIA".equals(tarefaTipo))
            {
                prioridade=false;
                cadastrada=true;
            } else{
                cadastrada=false;//Informar erro
                JOptionPane.showMessageDialog(null, "Este n�o � um tipo de tarefa v�lido!");
            }
        }
        else if(relator.getTipoPerfil()=="Usu�rio")
        {
            JOptionPane.showMessageDialog(null, "Perfil n�o autorizado!");
        } 
    }
        
    public void descreverTarefa(Perfil relator, String descricao)
    {
        if("Administrador".equals(relator.getTipoPerfil()))
        {
            tarefaDescricao=descricao;
        }
    }
    
    public void iniciarTarefa(Perfil executor, LocalDate inicio, int prazoDias)
    {
        if("Usu�rio".equals(executor.getTipoPerfil())&&cadastrada&&!iniciada&&!descartada&&!concluida&&!emAndamento)
        {
            setTarefaExecutor(executor);
            
            tarefaInicio=inicio;
        
            if(tarefaInicio.isBefore(hoje))
            {
                //Informar erro
                JOptionPane.showMessageDialog(null, "Esta data antecede o dia de hoje! Entre com outra!");

            } else if((prazoDias%30)==0)
            { 
                mes = prazoDias/30;

                tarefaConclusaoPrevista=inicio.plusMonths(mes);
                
                iniciada = true;
                emAndamento=true;
                
                } else if ((prazoDias/30)<1)
                {
                tarefaConclusaoPrevista=inicio.plusDays(prazoDias);
                iniciada = true;
                emAndamento=true;
                
                } else {
                    
                mes = (prazoDias - prazoDias%30)/30;
                dias=prazoDias%30;

                tarefaConclusaoPrevista=inicio.plusDays(dias);
                tarefaConclusaoPrevista=tarefaConclusaoPrevista.plusMonths(mes);
                
                iniciada = true;
                emAndamento=true;
            }
            
        } else if(concluida)
        {
            //Informar erro
            JOptionPane.showMessageDialog(null, "Esta tarefa j� foi conclu�da!");
        } else if(iniciada) 
        {
            //Informar erro
            JOptionPane.showMessageDialog(null, "Esta tarefa j� foi iniciada!");
        } else if(executor.getTipoPerfil()!="Usu�rio")
        {
            JOptionPane.showMessageDialog(null, "Acesse com perfil USU�RIO");
        } else if(executor.getNomeUsuario()!=tarefaExecutor.getNomeUsuario())
                {
                    JOptionPane.showMessageDialog(null,"Este n�o � o executor cadastrado!");
                }
    }
    
    public void concluirTarefa(Perfil executor, String solucao, LocalDate dataCONCLUSAO, boolean concluida)
    {
        /*Rever o atributo de entrada CONCLUIDA*/
        if ("Usu�rio".equals(executor.getTipoPerfil())&&executor.getNomeUsuario().equals(tarefaExecutor.getNomeUsuario())&&cadastrada&&iniciada&&!descartada&&concluida&&emAndamento&&dataCONCLUSAO.isAfter(tarefaInicio)||dataCONCLUSAO.isEqual(tarefaInicio))
        {            
        tarefaSolucao=solucao;
        tarefaConclusao=dataCONCLUSAO;
              
        //Adicionar o tempo gasto para conclus�o
        tempoDias = Period.between(tarefaInicio,tarefaConclusao).getDays()+30*Period.between(tarefaInicio,tarefaConclusao).getMonths()+360*Period.between(tarefaInicio,tarefaConclusao).getYears();
        tempoHoras = tempoDias*8;
        
        this.concluida = true;
        emAndamento=false;
        
        
        JOptionPane.showMessageDialog(null, "Solu��o da tarefa: "+getTarefaSolucao());
        JOptionPane.showMessageDialog(null, "Descri��o da tarefa: "+getTarefaDescricao());
        
        } else if(executor.getTipoPerfil()!="Usu�rio")
        {
            JOptionPane.showMessageDialog(null, "Acesse com perfil USU�RIO");
            
        } else if(executor.getNomeUsuario()!=tarefaExecutor.getNomeUsuario())
        {
            JOptionPane.showMessageDialog(null,"Este n�o � o executor cadastrado!");
        } else if(descartada)
        {
            JOptionPane.showMessageDialog(null,"Tarefa descartada!");
        } else if(this.concluida)
        {
            JOptionPane.showMessageDialog(null,"Tarefa j� foi conclu�da");
        } else {
            this.concluida = false;
        }
        
        if(tarefaConclusao.isAfter(tarefaConclusaoPrevista))
        {
            int tempo = Period.between(tarefaConclusaoPrevista,tarefaConclusao).getDays()+30*Period.between(tarefaConclusaoPrevista,tarefaConclusao).getMonths()+360*Period.between(tarefaConclusaoPrevista,tarefaConclusao).getYears();
            JOptionPane.showMessageDialog(null, "Tarefa conclu�da em "+getTarefaConclusao()+" levando "+getTempoDias()+" dias, somando um total de "+getTempoHoras()+" horas!");
            JOptionPane.showMessageDialog(null, "Tarefa conclu�da fora do prazo!\n\nData prevista: "+getTarefaConclusaoPrevista()+"\nData de conclus�o: "+getTarefaConclusao()+"\n\n"+tempo+" dias a mais que o previsto");
        
        } else if(tarefaConclusao.isEqual(tarefaConclusaoPrevista)||tarefaConclusao.isBefore(tarefaConclusaoPrevista)&&tarefaConclusao.isAfter(tarefaInicio))
        {
            JOptionPane.showMessageDialog(null, "Tarefa conclu�da dentro do prazo");
            JOptionPane.showMessageDialog(null, "Tarefa conclu�da em "+getTarefaConclusao()+" levando "+getTempoDias()+" dias, somando um total de "+getTempoHoras()+" horas!");
        } else 
        {
            JOptionPane.showMessageDialog(null, "Data inv�lida!");
        }
    }
    
    public void descartarTarefa(Perfil executor, boolean descarta)
    {
        //Adicionar condi��o para DESCARTADA
        if("Usu�rio".equals(executor.getTipoPerfil())&&executor.getNomeUsuario().equals(tarefaExecutor.getNomeUsuario())&&cadastrada&&iniciada&&!concluida&&descarta&&emAndamento)
        {
            emAndamento=false;
            descartada = true;
            
        } else if(executor.getTipoPerfil()!="Usu�rio")
        {
            JOptionPane.showMessageDialog(null, "Acesse com perfil USU�RIO");
            
        } else if(executor.getNomeUsuario()!=tarefaExecutor.getNomeUsuario())
        {
            JOptionPane.showMessageDialog(null,"Este n�o � o executor cadastrado!");
        } else if(concluida)
        {
            JOptionPane.showMessageDialog(null,"Tarefa j� foi conclu�da!");
        }
        else if(descartada)
        {
            JOptionPane.showMessageDialog(null,"Tarefa j� est� descartada!");
        } else {
            descartada = false;
        }
    }

    public String getEstado()
    {
        if(cadastrada&&!iniciada&&!concluida&&!descartada&&!emAndamento)
        {
            return estado= "Cadastrada";
        }
        if(cadastrada&&iniciada&&!concluida&&!descartada&&!emAndamento)
        {
            return estado= "Iniciada";
        }
        if(cadastrada&&iniciada&&concluida&&!descartada&&!emAndamento)
        {
            return estado= "Conclu�da";
        }
        if(cadastrada&&iniciada&&!concluida&&descartada&&!emAndamento)
        {
            return estado= "Descartada";
        }
        if(cadastrada&&iniciada&&!concluida&&!descartada&&emAndamento)
        {
            return estado= "Em andamento";
        }
        else
        {
            return "";
        }
        
    }

    public int getTempoDias() {
        return tempoDias;
    }

    public int getTempoHoras() {
        return tempoHoras;
    }

    public LocalDate getTarefaConclusaoPrevista() {
        return tarefaConclusaoPrevista;
    }

    public void setTarefaDescricao(String tarefaDescricao) {
        this.tarefaDescricao = tarefaDescricao;
    }
    

    public String getTarefaNome() 
    {
        return tarefaNome;
    }

    public void setTarefaRelator(Perfil tarefaRelator) {
        this.tarefaRelator = tarefaRelator;
    }

    public void setTarefaExecutor(Perfil tarefaExecutor) {
        this.tarefaExecutor = tarefaExecutor;
    }
    
    public Perfil getTarefaRelator() 
    {
        return tarefaRelator;
    }

    public Perfil getTarefaExecutor()
    {
        return tarefaExecutor;
    }

    public String getTarefaTipo() 
    {
        return tarefaTipo;
    }

    public String getTarefaDescricao() 
    {
        return tarefaDescricao;
    }

    public String getTarefaSolucao() 
    {
        return tarefaSolucao;
    }

    public boolean isConcluida() 
    {
        return concluida;
    }

    public boolean isPrioridade()
    {
        return prioridade;
    }

    public boolean isEmAndamento()
    {
        return emAndamento;
    }

    public LocalDate getTarefaConclusao() {
        return tarefaConclusao;
    }
    
    
}
