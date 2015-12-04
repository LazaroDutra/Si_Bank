/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList tip;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tip = FXCollections.observableArrayList();
        tip.add("Depósito");
        tip.add("Saque");
        tip.add("Transferência");
        tipo.setItems(tip);
        nConta.setMask("N!");
        nConta.setPromptText("Insira o número da conta");
        vTran.setMask("N!,N!");
        vTran.setPromptText("Insira o valor da transação");
        tConta.setMask("N!");
        tConta.setPromptText("Conta que receberar Transferência");
        tConta.setDisable(true);
        tcon.setDisable(true);
    }
    
    @FXML
    private void det(){
        if("Transferência".equals(tipo.getValue().toString())){
            tConta.setDisable(false);
            tcon.setDisable(false);
        }else{
            tConta.setDisable(true);
            tcon.setDisable(true);
        }
    }
    
}
