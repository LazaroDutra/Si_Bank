/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Lazaro
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MenuItem teste;
    //Cadastrar Cliente
    @FXML
    private void cadC(ActionEvent cadC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastroCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Cadastro de  Clientes");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Alterar Cliente
    @FXML
    private void altC(ActionEvent altC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlterarCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Cadastro de  Clientes");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Efetuar Transação
    @FXML
    private void cadT(ActionEvent cadT )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastroTFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Transação");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Remover Cliente
    @FXML
    private void remC(ActionEvent remC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Remover Cliente");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Remover Transação
    @FXML
    private void remT(ActionEvent remT )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemTFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Remover Transação");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Sobre
    @FXML
    private void sobre(ActionEvent sobre )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SobreFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Sobre");
         stage.initStyle(StageStyle.UNDECORATED);
         stage.setScene(new Scene(root1));  
         
         stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn;
        conn = new contest().conectar1();
        teste.setStyle( "-fx-cell-hover-color: #0093ff;");
        if(conn != null){
                System.out.println("Conexão efetuada com sucesso!"); 
            }else{
                System.out.println("Problemas!");
            }
        }  
    }    
