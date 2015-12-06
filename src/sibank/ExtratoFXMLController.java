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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    //Tabela Tranzações
    @FXML
    private TableView transacoes;
    @FXML
    private TableColumn idt;
    @FXML
    private TableColumn datat;
    @FXML
    private TableColumn vt;
    @FXML
    private TableColumn saldoant;
    @FXML
    private TableColumn tipo;
    
    private ObservableList<Transacao>Transacoes;
    /////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       conta.setMask("N!");
    }    
    
    @FXML
    public void ext(){
        contest conn = new contest();
        Transacoes = FXCollections.observableArrayList();
        try{
            Statement st = conn.conectar1().createStatement();
            ResultSet rs = st.executeQuery("SELECT transacoes.id_movimentacao, transacoes.data,  transacoes.valor, tipomov.descricao, transacoes.numeroconta, transacoes.saldoanterior FROM transacoes, tipomov, conta WHERE  transacoes.id_tipo = tipomov.id_tipo AND transacoes.numeroconta = conta.numeroconta and transacoes.numeroconta = "+conta.getText()+" AND (transacoes.data BETWEEN '"+datai.getValue().toString()+"' AND '"+dataf.getValue().toString()+"');");
            while(rs.next()){
                 Transacao tr = new Transacao(0,0,0,0,"","");
                 tr.setIdMov(rs.getInt("id_movimentacao"));
                 tr.setValorMov(rs.getInt("valor"));
                 tr.setTipoMov(rs.getString("tipomov.descricao"));
                 tr.setDataMov(rs.getString("data"));
                 tr.setSaldoAnt(rs.getInt("saldoanterior"));
                 tr.imp(tr);
                 Transacoes.add(tr);
            }
            idt.setCellValueFactory( new PropertyValueFactory("idMov"));
             saldoant.setCellValueFactory( new PropertyValueFactory("saldoAnt"));
             vt.setCellValueFactory( new PropertyValueFactory("valorMov"));
             tipo.setCellValueFactory(new PropertyValueFactory("tipoMov"));
             datat.setCellValueFactory( new PropertyValueFactory("dataMov"));
             
             transacoes.setItems(null);
             transacoes.setItems(Transacoes);
            
        }catch(SQLException ex){}
    }
}
