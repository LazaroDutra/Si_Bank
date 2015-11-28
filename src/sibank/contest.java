/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.sql.*;

/**
 *
 * @author Lazaro
 */
public class contest {
    /// DRIVER JDBC
    String driver = "com.mysql.jdbc.Driver";
    /// Nome do Banco
    String banco = "sibank";
    /// Nome do Host 
    String host = "localhost";
    /// String com Endereço de conexão 
    String str_conn= "jdbc:mysql://localhost/sibank";
    /// Usuario
    String usuario = "root";
    /// Senha
    String senha = "";
    
    
    public Connection conectar1() {
    /// Tentando conectar
    try{ 
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(str_conn,usuario,senha);
        return conn;
    
    }
    
    /// Caso tenha erro no driver  
    catch(ClassNotFoundException ex){
        System.out.println("Não foi possível carregar o driver.");
    }
    /// Caso tenha erro no banco
    catch(SQLException ex){
        System.out.println("Problema com o SQL");
    }
        return null;
    }
}
