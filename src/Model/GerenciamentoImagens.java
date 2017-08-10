/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Guilherme
 */
public class GerenciamentoImagens {
    
    public String getNovaImagem(){
        
        String caminhoDaImagem = getImagemPadrao(); 
        FileChooser escolheImagem = new FileChooser();
        FileChooser.ExtensionFilter extensoes = new FileChooser.ExtensionFilter("Imagens (*.jpg, *.png, *.jpeg) ", "*.jpg", "*.png", "*.jpeg");
        escolheImagem.getExtensionFilters().add(extensoes);
        File arquivo = escolheImagem.showOpenDialog(new Stage());
        if (arquivo != null) {
            caminhoDaImagem = arquivo.getAbsolutePath();
        }
        //caminhoDaImagem = arquivo.getAbsolutePath();
        return caminhoDaImagem;
        
    }
    
    public String getImagemPadrao(){
        return "C:\\Users\\Guilherme\\Desktop\\Fail.png";
    }
    
}
