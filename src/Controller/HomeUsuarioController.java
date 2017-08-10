/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.TCC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class HomeUsuarioController implements Initializable {

    @FXML
    private Button btnDocumentos;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnIngressos;

    @FXML
    private Button btnAleatorio;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnContas;

    @FXML
    private Button btnHome;
    
    @FXML
    private ImageView imgPerfil;
    
    public void acaoDosBotoes(){
        
        btnPerfil.setOnAction(event ->{
        
            TCC tcc = new TCC();
            tcc.abreTela("TelaPerfil");
            Stage stage = (Stage) btnPerfil.getScene().getWindow();
            stage.close();
            
        });
         
    }
    
    public void animacoes(){
        
        btnContas.setOnMouseEntered(event ->{
            btnContas.setStyle("-fx-background-color:#d02323 ");
        });
          
        btnContas.setOnMouseExited(event ->{
             btnContas.setStyle("-fx-background-color:#002363 ");
        });    
        btnHome.setOnMouseEntered(event ->{
            btnHome.setStyle("-fx-background-color:#d02323 ");
        });
        btnHome.setOnMouseExited(event ->{
            btnHome.setStyle("-fx-background-color:#002363 ");
       });    
        btnDocumentos.setOnMouseEntered(event ->{
            btnDocumentos.setStyle("-fx-background-color:#d02323 ");
        });
        btnDocumentos.setOnMouseExited(event ->{
            btnDocumentos.setStyle("-fx-background-color:#002363 ");
       });    
        btnIngressos.setOnMouseEntered(event ->{
            btnIngressos.setStyle("-fx-background-color:#d02323 ");
        });
        btnIngressos.setOnMouseExited(event ->{
            btnIngressos.setStyle("-fx-background-color:#002363 ");
       });    
        btnAleatorio.setOnMouseEntered(event ->{
            btnAleatorio.setStyle("-fx-background-color:#d02323 ");
        });
        btnAleatorio.setOnMouseExited(event ->{
            btnAleatorio.setStyle("-fx-background-color:#002363 ");
       });    
        btnLogout.setOnMouseEntered(event ->{
            btnLogout.setStyle("-fx-background-color:#d02323 ");
        });
        btnLogout.setOnMouseExited(event ->{
            btnLogout.setStyle("-fx-background-color:#002363 ");
       });    
        btnLogout.setOnAction(event ->{
            TCC tcc = new TCC();
            tcc.abreTela("TelaLogin");
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
        });
      
        

    }
    
    public void setarImagens(){
        String caminhoImagem = TCC.getLogada().getImagem();
        
        if(caminhoImagem == null){
            imgPerfil.setImage(new Image("file:///C:\\Users\\Guilherme\\Desktop\\TCC\\src\\imagens\\imgprofile.png"));
        } else {
        
        imgPerfil.setImage(new Image(caminhoImagem));
        }
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        acaoDosBotoes();
        animacoes();
        setarImagens();
        
    }    
    
}
