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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private TextField nConta;
    private String idcli,cont;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cpf.setMask("DDD.DDD.DDD-DD");
        cpf.setText(null);
    }
    @FXML
    private void pesq(){
        contest conn = new contest();
        try{
            Statement st = conn.conectar1().createStatement();
            ResultSet rs = st.executeQuery("Select id_cliente, nome From cliente Where cliente.cpf = '"+cpf.getText()+"';");
            while(rs.next()){
                nome.setText(rs.getString("nome"));
                idcli = rs.getString("id_cliente");
            }
            Statement st1 = conn.conectar1().createStatement();
            ResultSet rs1 = st1.executeQuery("Select numeroconta From conta where conta.id_cliente = '"+idcli+"'; ");
            while(rs1.next()){
                nConta.setText(rs1.getString("numeroconta"));
            }
        }catch(SQLException e ){}
    }
    @FXML
    private void rem(){
        contest conn = new contest();
        try{
            Statement st = conn.conectar1().createStatement();
            String deltran = "Delete from transacoes where transacoes.numeroconta ='"+cont+"'";
            st.executeUpdate(deltran);
            Statement st1 = conn.conectar1().createStatement();
            String delco = "Delete from conta where conta.id_cliente ='"+idcli+"'";
            st1.executeUpdate(delco);
            Statement st2 = conn.conectar1().createStatement();
            String delcli = "Delete from cliente where cliente.id_cliente ='"+idcli+"'";
            st2.executeUpdate(delcli);
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Remover Cliente");
            dialogoInfo.setHeaderText("Remoção Concluida");
            dialogoInfo.setContentText("Cliente removido com sucesso !!!");
            dialogoInfo.showAndWait();
            cpf.setText(null);
            nome.setText(null);
            nConta.setText(null);
        }catch(SQLException e ){}
    }
    
}
