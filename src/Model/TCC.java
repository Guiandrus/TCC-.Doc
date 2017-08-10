/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Aluno
 */
public class TCC extends Application {
    
    String nometela = "TelaLogin";
    
    public static Conta logada;

    public static Conta getLogada() {
        return logada;
    }

    public static void setLogada(Conta logada) {
        TCC.logada = logada;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/"+nometela+".fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle(".DOC");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/TCC.png")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void abreTela(String nomeTela){
        nometela = nomeTela;
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception ex) {
            Logger.getLogger(TCC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
