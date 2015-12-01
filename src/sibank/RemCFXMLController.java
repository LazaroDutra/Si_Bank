/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import util.MaskTextField;

/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class RemCFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MaskField cpf;
    @FXML
    private TextField nome;
    @FXML
    private MaskTextField nConta;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cpf.setMask("DDD.DDD.DDD-DD");
        cpf.setPromptText("Insira o C.P.F do cliente");
        nConta.setMask("N!,N!");
    }    
    
}
