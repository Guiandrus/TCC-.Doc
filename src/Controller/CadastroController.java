/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.EncriptaDecriptaOTP;
import Model.GerenciamentoImagens;
import Model.ModelDAO.ContaDAO;
import Model.TCC;
import Model.Validacao;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CadastroController implements Initializable {

    @FXML
    private Button btnEscolheImagem;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnCadastrar;

    @FXML
    private ImageView imageX;

    @FXML
    private ComboBox<String> cbPergunta;

    @FXML
    private ImageView imagemRosto;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfResposta;

    @FXML
    private TextField tfNome;
        
    @FXML
    private ImageView imageVoltar;

    @FXML
    private JFXButton btnVoltar;

    Validacao valida = new Validacao();
    
    boolean validacao;
    
    public static String imagemSelec;
    
    public void carregaComboBox(){
    
    ObservableList<String> tipoMaterial = FXCollections.observableArrayList();
    tipoMaterial.addAll("Professor favorito","Primeiro animal de estimação","Nome do pai","Nome da mãe","Banda favorita","Nome da primeira pessoa com quem você namorou");
    cbPergunta.setItems(tipoMaterial);
        
    }
    
    public void acaoDosBotoes(){
        
        btnEscolheImagem.setOnMouseClicked(event -> {
           
            GerenciamentoImagens gerenciar = new GerenciamentoImagens();
            String caminho = gerenciar.getNovaImagem();
            Image image = new Image("file:///" + caminho);
            imagemSelec = "file:///"+caminho;
            this.imagemRosto.setImage(image);
            
            
            
        });
        
        imageVoltar.setOnMouseClicked(event -> {
            Stage stage = (Stage) imageVoltar.getScene().getWindow();
            stage.close();
            TCC tcc = new TCC();
            tcc.abreTela("TelaLogin");       
        });
        
        imageVoltar.setOnMouseEntered(event -> {
            imageVoltar.setScaleX(1.1);
            imageVoltar.setScaleY(1.1);
            
        });
        imageVoltar.setOnMouseExited(event -> {
            imageVoltar.setScaleX(1);
            imageVoltar.setScaleY(1);
            
        });
        
        btnCadastrar.setOnAction(event ->{
            
        String nome,senha,login,email,tipoConta = null;
        String chave = null;
        String pergunta = cbPergunta.getValue();
        String resposta = null;
        String imagem = imagemSelec;
        
        nome = tfNome.getText();
        login = tfLogin.getText();
        email = tfEmail.getText();
        pergunta = cbPergunta.getValue();
        resposta = tfResposta.getText();
        
        EncriptaDecriptaOTP otp = new EncriptaDecriptaOTP();
        
                
            chave = otp.genKey(tfSenha.getText().length());
            String msgCriptografada = otp.criptografa(tfSenha.getText(), chave);
            String msgDecriptografada = otp.decriptografa(msgCriptografada, chave);


            tipoConta = "user";
            
            Conta conta = new Conta();
            conta.setNome(nome);
            conta.setEmail(email);
            conta.setLogin(login);
            conta.setSenha(msgCriptografada);
            conta.setTipoConta(tipoConta);
            conta.setResposta(resposta);
            conta.setPergunta(pergunta);
            conta.setChave(chave);
            conta.setImagem(imagem);
            validacao = valida.validaemail(conta.getEmail());
            
            if(validacao == true){
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Operação realizada com sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Cadastro do usuário efetuado com sucesso");
            alert.showAndWait();
             
            ContaDAO dao = new ContaDAO();
            dao.insert(conta);
            TCC tcc = new TCC();
            tcc.abreTela("TelaLogin");
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            stage.close();
                
            }
            
            else{
                
                
            }
        });
    }
    
    public void animacoes(){
        btnCadastrar.setOnMouseEntered(event ->{
          btnCadastrar.setScaleX(1.1);
          btnCadastrar.setScaleY(1.1);
        
        });
        btnCadastrar.setOnMouseExited(event ->{
          btnCadastrar.setScaleX(1);
          btnCadastrar.setScaleY(1);
    });    
        btnEscolheImagem.setOnMouseExited(event ->{
          btnEscolheImagem.setScaleX(1);
          btnEscolheImagem.setScaleY(1);
    });    
        btnEscolheImagem.setOnMouseEntered(event ->{
          btnEscolheImagem.setScaleX(1.1);
          btnEscolheImagem.setScaleY(1.1);
           
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
    
    public void setaimagem(){
        imagemRosto.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\profile-pictures.png"));
        imageX.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\cancel-button1.png"));
        imageVoltar.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\imagens\\left-arrow.png"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animacoes();
        acaoDosBotoes();
        setaimagem();
        carregaComboBox();
}
}
