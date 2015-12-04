/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class ProcurarCFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
        //Cliente
    @FXML
    private TextField nome;
    @FXML
    private DatePicker dataNas;
    @FXML
    private TextField cpf;
    @FXML
    private MaskField cpfp;
    @FXML
    private TextField email;
    @FXML
    private TextField conta;
    @FXML
    private TextField saldo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cpfp.setMask("DDD.DDD.DDD-DD");
        cpfp.setText(null);
    }

    @FXML
    private void pesq(){
        contest conn = new contest();
        try{
            Statement st = conn.conectar1().createStatement();
            ResultSet rs = st.executeQuery("SELECT cliente.nome,cliente.cpf,cliente.email,cliente.datanasc,conta.numeroconta,conta.saldo FROM `cliente`,`conta` where cliente.cpf = '"+cpfp.getText()+"' GROUP BY cliente.cpf");
            while(rs.next()){
                nome.setText(rs.getString("nome"));
                String data = String.valueOf(rs.getDate("datanasc"));
                dataNas.setValue(LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE));
                cpf.setText(rs.getString("cpf"));
                email.setText(rs.getString("email"));
                conta.setText(rs.getString("numeroconta"));
                saldo.setText(String.valueOf(rs.getFloat("saldo")));
            }
            
        }catch(SQLException ex){}
                 
    }
    @FXML
    private void extrato() throws IOException{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExtratoFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Extrato");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
}
