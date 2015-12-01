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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import util.MaskTextField;

/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class CadastroTFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Campos
    @FXML
    private ComboBox tipo;
    @FXML
    private MaskTextField nConta;
    @FXML
    private MaskTextField vTran;
    @FXML
    private DatePicker data;
    @FXML
    private MaskTextField tConta;
    @FXML
    private Label tcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nConta.setMask("N!");
        nConta.setPromptText("Insira o número da conta");
        vTran.setMask("N!,N!");
        vTran.setPromptText("Insira o valor da transação");
        tConta.setMask("N!");
        tConta.setPromptText("Conta que receberar Transferência");
        tConta.setDisable(true);
        tcon.setDisable(true);
    }    
    
}
