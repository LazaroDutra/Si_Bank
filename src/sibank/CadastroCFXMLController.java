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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cpf.setMask("DDD.DDD.DDD-DD");
        saldoini.setMask("N!,N!");
        cpf.setText(null);
        nome.setText(null);
        dataNas.setValue(null);
        dataAb.setValue(null);
        saldoini.setText("");
    }
    //Cadastrar
    @FXML
    private void cadastro(ActionEvent cad){
        if(nome.getText() == null | dataNas.getValue() == null| cpf.getText() == null | "".equals(saldoini.getText()) | dataAb.getValue()== null){
            System.out.println("Falta algum campo.");
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Cadastrar Cliente");
            dialogoInfo.setHeaderText("Cadastro não concluido");
            dialogoInfo.setContentText("Algum campo está vazio.");
            dialogoInfo.showAndWait();
        }else{
            try{
                Cliente c = new Cliente ("","","","");
                c.setNoCliente(nome.getText());
                c.setNaCliente(String.valueOf(dataNas.getValue()));
                c.setCpfCliente(cpf.getText());
                c.setEmCliente(email.getText());
                c.imprime(c);
                enc(c);
                selid(c);
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Cadastrar Cliente");
                dialogoInfo.setHeaderText("Cadastro concluido");
                dialogoInfo.setContentText("Cliente cadastrado com sucesso.");
                dialogoInfo.showAndWait();
                cpf.setText(null);
                nome.setText(null);
                dataNas.setValue(null);
                dataAb.setValue(null);
                email.setText(null);
                saldoini.setText("");
                }catch(NumberFormatException | SQLException e){}
        }
    }
    private static int enc(Cliente c) throws SQLException{         
        try{
            contest conn = new contest();
            Statement insert = conn.conectar1().createStatement();
            String in = "INSERT INTO cliente (id_cliente, datanasc, cpf, email, nome)  VALUES (NULL,'"+c.getNaCliente()+"','"+c.getCpCliente()+"','"+c.getEmCliente()+"','"+c.getNoCliente()+"');";
            return(insert.executeUpdate(in));
        }catch(SQLException e){
            System.out.println("Problema com o SQL: "+e);
        }
        return 0;    
    }
    //Conta
    private void selid(Cliente c){
        int id = 0;
        try{
           contest conn = new contest();
           Statement st = conn.conectar1().createStatement();
           ResultSet rs = st.executeQuery ("select id_cliente from cliente where cpf = '"+c.getCpCliente()+"';");
           while(rs.next()){
               System.out.println(rs.getString("id_cliente"));
               id = rs.getInt("id_cliente");
           }
           Conta co = new Conta(0,0,"");
           co.setDaAbertura(String.valueOf(dataAb.getValue()));
           co.setSaConta(Float.valueOf(saldoini.getText()));
           co.setIdCliente(id);
           co.imprime(co);
           Statement insert1 = conn.conectar1().createStatement();
           String in = "INSERT INTO conta (numeroconta, saldo, data_abertura, id_cliente)  VALUES (NULL,'"+Float.valueOf(saldoini.getText())+"','"+String.valueOf(dataAb.getValue())+"','"+id+"');";
           insert1.executeUpdate(in);
        }catch(SQLException e){
            System.out.println("Problema com o SQL: "+e);
        }
    }
    //Cancelar
    @FXML
    private void can(){
    }
    
    
}
