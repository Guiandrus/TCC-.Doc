/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.Email;
import Model.EncriptaDecriptaOTP;
import Model.ModelDAO.ContaDAO;
import Model.TCC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class TelaRecuperarSenhaController implements Initializable {

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnConfirmarLogin;

    @FXML
    private Button btnFinalizar;

    @FXML
    private AnchorPane paneConfirmacao;

    @FXML
    private AnchorPane PaneFinal;

    @FXML
    private Text textPergunta;

    @FXML
    private Button btnConfirmarResposta;

    

    @FXML
    private TextField tfResposta;
    
    @FXML
    private ImageView VoltarLogin;
    
    public static int contarecuperar;
    
    public void acaoDosBotoes(){
            
        btnConfirmarLogin.setOnAction(event ->{
            
            ContaDAO dao = new ContaDAO();
            ObservableList<Conta> contas = contas = dao.select();
            String login = tfLogin.getText();
            
            for (int i = 0; i < contas.size(); i++) {
                
                if(login.equals(contas.get(i).getLogin())){
                contarecuperar = i;
                paneConfirmacao.setVisible(true);
                textPergunta.setText(contas.get(i).getPergunta());
            }
            }
        });
        
        VoltarLogin.setOnMouseClicked(event ->{
            TCC tcc = new TCC();
            tcc.abreTela("TelaLogin");
            Stage stage = (Stage) VoltarLogin.getScene().getWindow();
            // do what you have to do
            stage.close();
            
        });
        
        
        
        
        btnConfirmarResposta.setOnAction(event ->{
            
            EncriptaDecriptaOTP criptografia = new EncriptaDecriptaOTP();
            
            ContaDAO dao = new ContaDAO();
            
            ObservableList<Conta> contas = contas = dao.select();
            
            System.out.println("Textfield : "+tfResposta.getText()+" Teste :"+contas.get(contarecuperar).getResposta());
            
            if(tfResposta.getText().equals(contas.get(contarecuperar).getResposta())){
            
            Conta conta = new Conta();
            
            conta.setLogin(contas.get(contarecuperar).getLogin());
            
            String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

            String senha="";


            for (int x=0; x<10; x++){
                int j = (int) (Math.random()*carct.length);
                senha += carct[j];
                
            }
            String chave = criptografia.genKey(senha.length());
            String senhacripto = criptografia.criptografa(senha, chave);
            
            conta.setSenha(senhacripto);
            conta.setChave(chave);
            dao.update(conta);
                
                Email email = new Email();
                email.enviaEmail(contas.get(contarecuperar).getEmail(), senha);
                
                PaneFinal.setVisible(true);
                
            }
        });
        
        btnFinalizar.setOnAction(event ->{
            TCC tcc = new TCC();
            tcc.abreTela("TelaLogin");
            Stage stage = (Stage) btnFinalizar.getScene().getWindow();
            // do what you have to do
            stage.close();
        });
    }
    
    public void animaçaoBotoes(){
        
        VoltarLogin.setOnMouseEntered(event -> {
            VoltarLogin.setScaleX(1.1);
            VoltarLogin.setScaleY(1.1);
        });
        
        VoltarLogin.setOnMouseExited(event -> {
            VoltarLogin.setScaleX(1);
            VoltarLogin.setScaleY(1);
        
        });
   
        
    }
    
    public void setaImagem(){
        
        VoltarLogin.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\left-arrow.png"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setaImagem();
        acaoDosBotoes();
        animaçaoBotoes();
        paneConfirmacao.setVisible(false);
        PaneFinal.setVisible(false);
        
    }    
    
}
