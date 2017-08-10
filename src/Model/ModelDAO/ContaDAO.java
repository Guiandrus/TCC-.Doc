/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ModelDAO;

import Model.Conta;
import Model.jbdc.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Guilherme
 */
public class ContaDAO {
    
    public ObservableList<Conta> select(){
        
        ObservableList<Conta> contas = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM conta";
        
        
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Conta conta = new Conta();
                
                
                conta.setEmail(rs.getString("email"));
                conta.setLogin(rs.getString("login"));
                conta.setSenha(rs.getString("senha"));
                conta.setNome(rs.getString("nome"));
                conta.setTipoConta(rs.getString("tipoconta"));
                conta.setChave(rs.getString("chave"));
                conta.setPergunta(rs.getString("pergunta"));
                conta.setResposta(rs.getString("resposta"));
                conta.setImagem(rs.getString("imagem"));
                
                contas.add(conta);
                
                
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return contas;
        
    }
    
    public void insert (Conta conta){
    
        String sql = "INSERT INTO conta (nome, email, login, senha, tipoconta, chave, pergunta, resposta, imagem)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ConnectionFactory con = new ConnectionFactory();
        
        PreparedStatement stmt;
        try {
            
            stmt = con.getConnection().prepareStatement(sql);
            
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getLogin());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getTipoConta());
            stmt.setString(6, conta.getChave());
            stmt.setString(7, conta.getPergunta());
            stmt.setString(8, conta.getResposta());
            stmt.setString(9, conta.getImagem());
            
            stmt.execute();
            
            
        } catch (SQLException ex) {
            System.out.println("Erro : "+ex.getLocalizedMessage());
        }
        
    }
        public void delete (Conta conta){
            
        String sql = "DELETE FROM conta WHERE login = ?";
        
        ConnectionFactory con = new ConnectionFactory();
        
        PreparedStatement stmt;
        
            try {
                
                stmt = con.getConnection().prepareStatement(sql);
                
                stmt.setString(1, conta.getLogin());
                
                stmt.execute();
                
                stmt.close();
            } catch (Exception e) {
                
            }
            
        }
        
        public void update (Conta conta){
            
            String sql = "UPDATE conta SET senha = ?, chave = ?, nome = ?, email = ?, imagem = ? where login = ?";
            
            ConnectionFactory con = new ConnectionFactory();
            
            PreparedStatement stmt;
            
            try {
                
            stmt = con.getConnection().prepareStatement(sql);
            
            
            stmt.setString(1, conta.getSenha());
            stmt.setString(2, conta.getChave());
            stmt.setString(3, conta.getNome());
            stmt.setString(4, conta.getEmail());
            stmt.setString(5, conta.getImagem());
            stmt.setString(6, conta.getLogin());
            
            
            stmt.execute();
            
                
            } catch (Exception e) {
                System.out.println("Erro : "+e.getLocalizedMessage());
            }
        
            
        }
            
            
        
    
    static int contaLogada;
    private static ObservableList<Conta> listaDeContas = FXCollections.observableArrayList();
    
    
    public ObservableList<Conta> getConta(){
        return listaDeContas;
    }
    
    public int contaLogada(){
        int contaLogadaAgora = contaLogada;
        return contaLogadaAgora;
    }
    
}
