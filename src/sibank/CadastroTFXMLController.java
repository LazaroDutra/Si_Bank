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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    @FXML
    private void tran(){
        float sa =0, vt = vt = Float.parseFloat(vTran.getText());
        
        contest conn = new contest();
        if("Depósito".equals(tipo.getValue().toString())){
            try{
                Statement st = conn.conectar1().createStatement();
                String dep = "Call faz_deposito("+vTran.getText()+","+nConta.getText()+" )";
                st.executeQuery(dep);
               
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Efetuar Transação");
                dialogoInfo.setHeaderText("Transação concluida");
                dialogoInfo.setContentText("Transação efetuada com sucesso.");
                dialogoInfo.showAndWait();
                zera();
            }catch(SQLException e){}
        }
        if("Saque".equals(tipo.getValue().toString())){
             try{
                Statement sts = conn.conectar1().createStatement();
                ResultSet rs = sts.executeQuery("Select saldo from conta where numeroconta = "+nConta.getText()+"");
                while(rs.next()){
                    sa = rs.getFloat("saldo");
                    System.out.println(sa);
                }
                if(vt <= sa){
                    Statement st = conn.conectar1().createStatement();
                    String dep = "Call faz_saque('"+vTran.getText()+"','"+nConta.getText()+"')";
                    st.executeQuery(dep);
                    
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Efetuar Transação");
                    dialogoInfo.setHeaderText("Transação concluida");
                    dialogoInfo.setContentText("Transação efetuada com sucesso.");
                    dialogoInfo.showAndWait();
                    zera();
                }else{
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Efetuar Transação");
                    dialogoInfo.setHeaderText("Transação não efetuada");
                    dialogoInfo.setContentText("Saldo insuficiente");
                    dialogoInfo.showAndWait();
                }
            }catch(SQLException e){}
        }
        if("Transferência".equals(tipo.getValue().toString())){
             try{
                Statement sts = conn.conectar1().createStatement();
                ResultSet rs = sts.executeQuery("Select saldo from conta where numeroconta = "+nConta.getText()+"");
                while(rs.next()){
                    sa = rs.getFloat("saldo");
                    System.out.println(sa);
                }
                if(vt <= sa){
                    Statement st = conn.conectar1().createStatement();
                    String dep = "Call faz_transferencia('"+nConta.getText()+"', '"+vTran.getText()+"', '"+tConta.getText()+"')";
                    st.executeQuery(dep);
                
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Efetuar Transação");
                    dialogoInfo.setHeaderText("Transação concluida");
                    dialogoInfo.setContentText("Transação efetuada com sucesso.");
                    dialogoInfo.showAndWait();
                    zera();
                }else{
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Efetuar Transação");
                    dialogoInfo.setHeaderText("Transação não efetuada");
                    dialogoInfo.setContentText("Saldo insuficiente");
                    dialogoInfo.showAndWait();
                }
            }catch(SQLException e){}
        }
        
    }
    
    private void zera(){
        nConta.setText("");
        vTran.setText("");
        tConta.setText("");
        tipo.setValue(null);
    }
    
}
