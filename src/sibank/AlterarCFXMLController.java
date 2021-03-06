/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        cpfp.setPromptText("Insira o C.P.F do cliente");
        cpf.setMask("DDD.DDD.DDD-DD");
        cpfp.setMask("DDD.DDD.DDD-DD");
        cpf.setText(null);
        cpfp.setText(null);
    }
    
    @FXML
    private void pesq(){
        contest conn = new contest();
        try{
            Statement st = conn.conectar1().createStatement();
            ResultSet rs = st.executeQuery("Select * From cliente where cpf = '"+cpfp.getText()+"';");
            while(rs.next()){
                nome.setText(rs.getString("nome"));
                String data = String.valueOf(rs.getDate("datanasc"));
                dataNas.setValue(LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE));
                cpf.setText(rs.getString("cpf"));
                email.setText(rs.getString("email"));
            }
        }catch(SQLException ex){}
    }
    
    @FXML
    private void alterar(){
        contest conn = new contest();
        try{
            Statement st = conn.conectar1().createStatement();
            String alt = "Update cliente Set nome = '"+nome.getText()+"', datanasc = '"+dataNas.getValue()+"', cpf = '"+cpf.getText()+"', email = '"+email.getText()+"' WHERE cpf = '"+cpfp.getText()+"';";
            st.executeUpdate(alt);
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Alterar Cliente");
            dialogoInfo.setHeaderText("Alteração Concluida");
            dialogoInfo.setContentText("Cliente alterado com sucesso !!!");
            dialogoInfo.showAndWait();
            cpfp.setText(null);
            cpf.setText(null);
            nome.setText(null);
            dataNas.setValue(null);
            email.setText(null);
            pesq();
        }catch(SQLException ex){}
    }
}
