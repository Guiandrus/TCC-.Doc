/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.EncriptaDecriptaOTP;
import Model.ModelDAO.ContaDAO;
import Model.TCC;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private JFXPasswordField tfSenha;

    @FXML
    private JFXTextField tfLogin;

    @FXML
    private JFXButton btnLogar;
    
    @FXML
    private Button btnSair;
    
    @FXML
    private ImageView imgLogo;

    @FXML
    private Button btnCadastrar;
    
    @FXML
    private ImageView imagemFundo;
    
    @FXML
    private ImageView imageX;
    
    @FXML
    private Hyperlink EsquecerSenha;
    
    @FXML
    private StackPane stackPane;
    
    EncriptaDecriptaOTP criptografia = new EncriptaDecriptaOTP();
    
    public void animacoes(){
        
        tfLogin.setStyle("-fx-prompt-text-fill: white; -fx-text-fill:white");
        tfSenha.setStyle("-fx-prompt-text-fill: white; -fx-text-fill:white");
        
        
         btnCadastrar.setOnAction(event ->{
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            stage.close();
            TCC tcc = new TCC();
            tcc.abreTela("Cadastro");
        });
        btnCadastrar.setOnMouseEntered(event ->{
          btnCadastrar.setScaleX(1.1);
          btnCadastrar.setScaleY(1.1);
        
        });
        btnCadastrar.setOnMouseExited(event ->{
          btnCadastrar.setScaleX(1);
          btnCadastrar.setScaleY(1);
    });    
        btnLogar.setOnMouseExited(event ->{
          btnLogar.setScaleX(1);
          btnLogar.setScaleY(1);
    });    
        btnLogar.setOnMouseEntered(event ->{
          btnLogar.setScaleX(1.1);
          btnLogar.setScaleY(1.1);
           
                }); 
        
        btnSair.setOnAction(event ->{
        Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        });
        
        btnSair.setOnMouseEntered(event ->{
            imageX.setScaleX(1.1);
            imageX.setScaleY(1.1);
            
        });
        
        btnSair.setOnMouseExited(event ->{
            imageX.setScaleX(1);
            imageX.setScaleY(1);
            
        });
        
    }
    
    public void logar(){
        String login = tfLogin.getText();
        String senha = tfSenha.getText();
            
            ContaDAO dao = new ContaDAO();
            
            if("admin".equals(login) && "admin".equals(senha)){
                try {
                    TCC tcc = new TCC();
                    tcc.abreTela("HomeUsuario");
                    Stage stage = (Stage) btnLogar.getScene().getWindow();
                        // do what you have to do
                        stage.close();
                } catch (Exception e) {
                }
                

            } else {
            ObservableList<Conta> contas = contas = dao.select();
            for (int i = 0; i < contas.size(); i++) {
                
                String senhadescriptografada = criptografia.decriptografa(contas.get(i).getSenha(),contas.get(i).getChave());
                
                if(login.equals(contas.get(i).getLogin()) && senha.equals(senhadescriptografada)){
                        try {
                        TCC tcc = new TCC();
                        TCC.setLogada(contas.get(i));
                        tcc.abreTela("HomeUsuario");
                        
                        Stage stage = (Stage) btnLogar.getScene().getWindow();
                        // do what you have to do
                        stage.close();
             
         
                    } catch (Exception e) {
                            System.out.println(""+e.getLocalizedMessage());
}
}
}
}
       
    }
    
    public void acaoDosBotoes(){
        
        tfSenha.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                logar();
            }
            }
        });
        
        
        EsquecerSenha.setOnAction(event ->{
            TCC tcc = new TCC();
            tcc.abreTela("TelaRecuperarSenha");
            Stage stage = (Stage) btnLogar.getScene().getWindow();
            // do what you have to do
            stage.close();
        });
       
        btnLogar.setOnAction(event ->{
    
        logar();
        
        });
            
}
    
  
    
    public void setaimagem(){
        imgLogo.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\TCC.png"));
        imagemFundo.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\FifaAzul.png"));
        imageX.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\cancel-button.png"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acaoDosBotoes();
        setaimagem();
        animacoes();
}
}
