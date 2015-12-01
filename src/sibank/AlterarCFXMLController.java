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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class AlterarCFXMLController implements Initializable {

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
    private MaskField cpfp;
    @FXML
    private TextField email;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cpf.setMask("DDD.DDD.DDD-DD");
        cpfp.setMask("DDD.DDD.DDD-DD");
    }    
    
}
