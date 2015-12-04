/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lazaro
 */
public class Cliente {
    private final SimpleIntegerProperty idCliente = new SimpleIntegerProperty();    
    private final SimpleStringProperty noCliente = new SimpleStringProperty();
    private final SimpleStringProperty naCliente = new SimpleStringProperty();
    private final SimpleStringProperty contaCliente = new SimpleStringProperty();
    private final SimpleStringProperty cpCliente = new SimpleStringProperty();
    private final SimpleStringProperty emCliente = new SimpleStringProperty();
    private ArrayList <Cliente> Clientes;

    public Cliente (String noCliente, String cpCliente,String emCliente,String naCliente) {
        this.noCliente.set(noCliente);
        this.cpCliente.set(cpCliente);
        this.naCliente.set(naCliente);
        this.emCliente.set(emCliente);
        Clientes = new ArrayList();

    }
    public Cliente (int idCliente,String contaCliente, String noCliente, String cpCliente) {
        this.idCliente.set(idCliente);
        this.noCliente.set(noCliente);
        this.cpCliente.set(cpCliente);
        this.contaCliente.set(contaCliente);
        Clientes = new ArrayList();
    }

   
    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int Idaluno) {
        this.idCliente.set(Idaluno);
    }

    public String getNoCliente() {
        return noCliente.get();
    }

    public void setNoCliente(String NoCliente) {
        this.noCliente.set(NoCliente);
    }

    public String getCpCliente() {
        return cpCliente.get();
    }

    public void setCpfCliente(String cpCliente) {
        this.cpCliente.set(cpCliente);
    }

    public String getNaCliente() {
        return naCliente.get();
    }

    public void setNaCliente(String naCliente) {
        this.naCliente.set(naCliente);
    }
    
    public String getContaCliente() {
        return contaCliente.get();
    }

    public void setContaCliente(String naCliente) {
        this.contaCliente.set(naCliente);
    }
    
    public String getEmCliente() {
        return emCliente.get();
    }

    public void setEmCliente(String emCliente) {
        this.emCliente.set(emCliente);
    }

    
    public void adL(Cliente c){
        Clientes.add(c);
    }
    public void imprime(Cliente c){
        System.out.println("Nome do Cliente: "+c.getNoCliente());
        System.out.println("Data de Nascimento: "+c.getNaCliente());
        System.out.println("C.P.F: "+c.getCpCliente());
        System.out.println("Email: "+c.getEmCliente());
        System.out.println("Conta: "+c.getContaCliente());
    }
   
}
