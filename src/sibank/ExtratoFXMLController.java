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
import util.MaskTextField;

/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class ExtratoFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */    
    @FXML
    private DatePicker datai;
    @FXML
    private DatePicker dataf;
    @FXML
    private MaskTextField conta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       conta.setMask("N!");
    }    
    
}
