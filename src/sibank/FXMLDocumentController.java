/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Lazaro
 */
public class FXMLDocumentController implements Initializable {
    

    //Cadastrar Cliente
    @FXML
    private void cadC(ActionEvent cadC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastroCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Cadastro de  Clientes");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Alterar Cliente
    @FXML
    private void altC(ActionEvent altC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlterarCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Alterar  Clientes");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Efetuar Transação
    @FXML
    private void cadT(ActionEvent cadT )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastroTFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Efetuar Transação");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Procurar Cliente
    @FXML
    private void proC(ActionEvent proC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProcurarCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Procurar Cliente");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Remover Cliente
    @FXML
    private void remC(ActionEvent remC )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemCFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Remover Cliente");
         stage.setResizable(false);
         stage.setScene(new Scene(root1));  
         stage.show();
    }
    //Sobre
    @FXML
    private void sobre(ActionEvent sobre )  throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SobreFXML.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Sobre");
         stage.initStyle(StageStyle.UNDECORATED);
         stage.setScene(new Scene(root1));  
         
         stage.show();
    }
    
    
    //Tabelas
    @FXML
    private TableView clientes;
    @FXML
    private TableView contas;
    @FXML
    private TableView transacoes;
    /////////////////////////////
    
    //Tabela Clientes
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn nome;
    @FXML
    private TableColumn cpf;
    @FXML
    private TableColumn nconta;
    
    private ObservableList<Cliente>Clientes;
    ///////////////////////////
    
    //Tabela Contas
    @FXML
    private TableColumn numconta;
    @FXML
    private TableColumn saldo;
    @FXML
    private TableColumn cli;
    @FXML
    private TableColumn datAb;
    private ObservableList<Conta>Contas;
    //////////////////////////////
    
    //Tabela Tranzações
    @FXML
    private TableColumn idt;
    @FXML
    private TableColumn datat;
    @FXML
    private TableColumn vt;
    @FXML
    private TableColumn nuconta;
    @FXML
    private TableColumn saldoant;
    
    /////////////////////////////
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn;
        conn = new contest().conectar1();
        if(conn != null){
                System.out.println("Conexão efetuada com sucesso!"); 
            }else{
                System.out.println("Problemas!");
            }
        }
    @FXML
     public void adt(){
         contest conn = new contest();
         Clientes = FXCollections.observableArrayList();
         Contas = FXCollections.observableArrayList();
         assert clientes != null :"fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
         assert contas != null :"fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
         assert transacoes != null :"fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
         
         try{
             Statement st = conn.conectar1().createStatement();
             ResultSet rs = st.executeQuery("SELECT cliente.id_cliente,cliente.nome,cliente.cpf,conta.numeroconta FROM `cliente`,`conta` where conta.id_cliente = cliente.id_cliente;");
             while(rs.next()){
                 Cliente c = new Cliente(0,"","","");
                 c.setIdCliente(rs.getInt("id_cliente"));
                 c.setNoCliente(rs.getString("nome"));
                 c.setCpfCliente(rs.getString("cpf"));
                 c.setContaCliente(rs.getString("conta.numeroconta"));
                 c.imprime(c);
                 Clientes.add(c);
             }
             id.setCellValueFactory( new PropertyValueFactory("idCliente"));
             nome.setCellValueFactory( new PropertyValueFactory("noCliente"));
             cpf.setCellValueFactory( new PropertyValueFactory("cpCliente"));
             nconta.setCellValueFactory( new PropertyValueFactory("contaCliente"));
             clientes.setItems(null);
             clientes.setItems(Clientes);
             ///////////////////////////////////////////////////////////////////////////
             
             //Tabela Contas
             Statement st1 = conn.conectar1().createStatement();
             ResultSet rs1 = st1.executeQuery("SELECT numeroconta, saldo,data_abertura, cliente.nome FROM `conta`, cliente WHERE cliente.id_cliente = conta.id_cliente;");
             while(rs1.next()){
                 Conta co = new Conta(0,0,"","");
                 co.setNuConta(rs1.getInt("numeroconta"));
                 co.setSaConta(rs1.getFloat("saldo"));
                 co.setNomeCliente(rs1.getString("nome"));
                 co.setDaAbertura(rs1.getString("data_abertura"));
                 co.imprime(co);
                 Contas.add(co);
             }
             numconta.setCellValueFactory( new PropertyValueFactory("nuConta"));
             saldo.setCellValueFactory( new PropertyValueFactory("saConta"));
             cli.setCellValueFactory( new PropertyValueFactory("nomeCliente"));
             datAb.setCellValueFactory( new PropertyValueFactory("daAbertura"));
             contas.setItems(null);
             contas.setItems(Contas);
         }catch (SQLException ex) {}
     }
    }    
