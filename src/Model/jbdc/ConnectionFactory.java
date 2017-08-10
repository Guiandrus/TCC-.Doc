
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Aluno
 */
public class ConnectionFactory {

    private String bancoDeDados = "TCC";
    private String usuario = "postgres";
    private String senha = "aluno";
    
    public static void main(String[] args) {
        ConnectionFactory con = new ConnectionFactory();
        con.getConnection();
    }
    
    public Connection getConnection(){
        
        Connection connect = null;
        
        try{
          
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/" + bancoDeDados, usuario, senha);
            
            System.out.println("funfou");
            
        } catch (Exception e){
            System.out.println("erro: "+e.getLocalizedMessage());
        } 
        return connect;
    }
}
    

