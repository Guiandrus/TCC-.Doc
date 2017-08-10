/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.CadastroController.imagemSelec;
import Model.Conta;
import Model.EncriptaDecriptaOTP;
import Model.GerenciamentoImagens;
import Model.ModelDAO.ContaDAO;
import Model.TCC;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Event;
import static java.awt.SystemColor.text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class TelaPerfilController implements Initializable {

    @FXML
    private JFXColorPicker corTema;
        
    @FXML
    private Button btnConfirmar;

    @FXML
    private ImageView profilePicture;

    @FXML
    private JFXTextField tfSenha;

    @FXML
    private JFXTextField tfNovaSenha;

    @FXML
    private ImageView fotoPerfil;

    @FXML
    private Label labelEmail;
    
    @FXML
    private JFXButton btnEditar;

    @FXML
    private Label labelNome;

    @FXML
    private Button btnCancelar;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private Pane paneAlteracao;

    @FXML
    private Button btnAlterarImagem;

    @FXML
    private JFXTextField tfNome;
    
    @FXML
    private ImageView imageVoltar;
    
    Conta conta = new Conta();
    
    EncriptaDecriptaOTP criptografia = new EncriptaDecriptaOTP();

    public void acaoDosBotoes(){
  
        corTema.setOnAction(event ->{
        String cor = corTema.getValue().toString();
        btnEditar.setStyle("-fx-background-color:"+cor.toString().replace("0x", "#"));
        });
        
        atualizaDados();
        btnEditar.setOnAction(event ->{

        paneAlteracao.setVisible(true);
        
        });
        
        btnCancelar.setOnAction(event ->{
        paneAlteracao.setVisible(false);
        });
        
        btnAlterarImagem.setOnAction(event ->{
        
            GerenciamentoImagens gerenciar = new GerenciamentoImagens();
            String caminho = gerenciar.getNovaImagem();
            Image image = new Image("file:///" + caminho);
            imagemSelec = "file:///"+caminho;
            this.profilePicture.setImage(image);
            this.fotoPerfil.setImage(image);
            
        });
        
        btnConfirmar.setOnAction(event ->{
        
            Conta alterada = new Conta();
            alterada.setEmail(tfEmail.getText());
            if(imagemSelec != null){
            alterada.setImagem(imagemSelec);                          
            } else {
                imagemSelec = conta.getImagem();
            }
            alterada.setNome(tfNome.getText());
            alterada.setLogin(conta.getLogin());
            String chave = criptografia.genKey(tfNovaSenha.getLength());
            String senha = criptografia.criptografa(tfNovaSenha.getText(), chave);
            
            alterada.setSenha(senha);
            alterada.setChave(chave);
            
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.update(alterada);

            atualizaDados();
        });
        
         imageVoltar.setOnMouseClicked(event -> {
            Stage stage = (Stage) imageVoltar.getScene().getWindow();
            stage.close();
            TCC tcc = new TCC();
            tcc.abreTela("HomeUsuario");
        });
        
    }
    
    public void animaçãodosbotão(){
        
        imageVoltar.setOnMouseEntered(event ->{
        imageVoltar.setScaleX(1.1);
        imageVoltar.setScaleY(1.1);     
        });
        
        imageVoltar.setOnMouseExited(event -> {
        imageVoltar.setScaleX(1);
        imageVoltar.setScaleY(1);
        
        });
    }
    
    public void atualizaDados(){           
        labelNome.setText(conta.getNome());
        labelEmail.setText(conta.getEmail());
        //profilePicture.setImage(new Image(""+conta.getImagem())); 
        //fotoPerfil.setImage(new Image(""+conta.getImagem())); 
        tfNome.setText(conta.getNome());
        tfEmail.setText(conta.getEmail());
        String senha = criptografia.decriptografa(conta.getSenha(), conta.getChave());
        tfSenha.setText(senha); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        conta = TCC.getLogada();
        
        acaoDosBotoes();
        animaçãodosbotão();
        
    }    
    
}
