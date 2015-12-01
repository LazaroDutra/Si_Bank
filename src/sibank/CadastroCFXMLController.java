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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import util.MaskTextField;
/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class CadastroCFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Cliente
    @FXML
    private TextField nome;
    @FXML
    private DatePicker dataNas;
    @FXML
    private MaskField cpf;
    @FXML
    private TextField email;
    
    //Conta
    @FXML
    private MaskTextField saldoini;
    @FXML
    private DatePicker dataAb;
    
    //Botões
    @FXML
    private Button cancel;
    
    //Cadastrar
    @FXML
    private void cad(){
    }
    //Cancelar
    @FXML
    private void can(){
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cpf.setMask("DDD.DDD.DDD-DD");
        saldoini.setMask("N!,N!");
    }
    
    
}
